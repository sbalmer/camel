/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.processor;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.LoggingExceptionHandler;
import org.apache.camel.impl.ServiceSupport;
import org.apache.camel.processor.resequencer.ResequencerEngine;
import org.apache.camel.processor.resequencer.SequenceElementComparator;
import org.apache.camel.processor.resequencer.SequenceSender;
import org.apache.camel.spi.ExceptionHandler;
import org.apache.camel.util.ServiceHelper;

/**
 * A resequencer that re-orders a (continuous) stream of {@link Exchange}s. The
 * algorithm implemented by {@link ResequencerEngine} is based on the detection
 * of gaps in a message stream rather than on a fixed batch size. Gap detection
 * in combination with timeouts removes the constraint of having to know the
 * number of messages of a sequence (i.e. the batch size) in advance.
 * <p>
 * Messages must contain a unique sequence number for which a predecessor and a
 * successor is known. For example a message with the sequence number 3 has a
 * predecessor message with the sequence number 2 and a successor message with
 * the sequence number 4. The message sequence 2,3,5 has a gap because the
 * sucessor of 3 is missing. The resequencer therefore has to retain message 5
 * until message 4 arrives (or a timeout occurs).
 * <p>
 * Instances of this class poll for {@link Exchange}s from a given
 * <code>endpoint</code>. Resequencing work and the delivery of messages to
 * the next <code>processor</code> is done within the single polling thread.
 * 
 * @author Martin Krasser
 * 
 * @version $Revision$
 * 
 * @see ResequencerEngine
 */
public class StreamResequencer extends ServiceSupport implements SequenceSender<Exchange>, Runnable, Processor {

    private ExceptionHandler exceptionHandler;
    private ResequencerEngine<Exchange> engine;
    private PollingConsumer<? extends Exchange> consumer;
    private Endpoint<? extends Exchange> endpoint;
    private Processor processor;
    private Thread worker;
    private int capacity;
    
    /**
     * Creates a new {@link StreamResequencer} instance.
     * 
     * @param endpoint
     *            endpoint to poll exchanges from.
     * @param processor
     *            next processor that processes re-ordered exchanges.
     * @param comparator
     *            a sequence element comparator for exchanges.
     */
    public StreamResequencer(Endpoint<? extends Exchange> endpoint, Processor processor, SequenceElementComparator<Exchange> comparator) {
        this.exceptionHandler = new LoggingExceptionHandler(getClass());
        this.engine = new ResequencerEngine<Exchange>(comparator);
        this.engine.setSequenceSender(this);
        this.endpoint = endpoint;
        this.processor = processor;
    }

    /**
     * Returns this resequencer's exception handler.
     * 
     * @return this resequencer's exception handler.
     */
    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    /**
     * Returns the next processor.
     * 
     * @return the next processor.
     */
    public Processor getProcessor() {
        return processor;
    }

    /**
     * Returns this resequencer's capacity. The capacity is the maximum number
     * of exchanges that can be managed by this resequencer at a given point in
     * time. If the capacity if reached, polling from the endpoint will be
     * skipped for <code>timeout</code> milliseconds giving exchanges the
     * possibility to time out and to be delivered after the waiting period.
     * 
     * @return this resequencer's capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns this resequencer's timeout. This sets the resequencer engine's
     * timeout via {@link ResequencerEngine#setTimeout(long)}. This value is
     * also used to define the polling timeout from the endpoint.
     * 
     * @return this resequencer's timeout.
     * (Processor) 
     * @see ResequencerEngine#setTimeout(long)
     */
    public long getTimeout() {
        return engine.getTimeout();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTimeout(long timeout) {
        engine.setTimeout(timeout);
    }

    @Override
    public String toString() {
        return "StreamResequencer[to: " + processor + "]";
    }

    @Override
    protected void doStart() throws Exception {
        consumer = endpoint.createPollingConsumer();
        ServiceHelper.startServices(processor, consumer);
        worker = new Thread(this, this + " Polling Thread");
        engine.start();
        worker.start();
    }

    @Override
    protected void doStop() throws Exception {
        // let's stop everything in the reverse order
        // no need to stop the worker thread -- it will stop automatically when this service is stopped
        engine.stop();
        ServiceHelper.stopServices(consumer, processor);
    }

    /**
     * Sends the <code>exchange</code> to the next <code>processor</code>.
     * 
     * @param o
     *            exchange to send.
     */
    public void sendElement(Exchange o) throws Exception {
        processor.process(o);
    }

    /**
     * Loops over {@link #processExchange()}.
     */
    public void run() {
        while (!isStopped() && !isStopping()) {
            try {
                processExchange();
            } catch (Exception e) {
                exceptionHandler.handleException(e);
            }
        }
    }

    /**
     * Processes an exchange received from the this resequencer's
     * <code>endpoint</code>. Received exchanges are processed via
     * {@link ResequencerEngine#insert(Object)}.
     * {@link ResequencerEngine#deliver()} is then called in any case regardless
     * whether a message was received or receiving timed out.
     * 
     * @throws Exception
     *             if exchange delivery fails.
     */
    protected void processExchange() throws Exception {
        if (engine.size() >= capacity) {
            Thread.sleep(getTimeout());
        } else {
            Exchange exchange = consumer.receive(getTimeout());
            if (exchange != null) {
                engine.insert(exchange);
            }
        }
        engine.deliver();
    }

    public void process(Exchange exchange) throws Exception {
        if (engine.size() >= capacity) {
            Thread.sleep(getTimeout());
        } else {
            if (exchange != null) {
                engine.insert(exchange);
            }
        }
        engine.deliver();
        
    }

}