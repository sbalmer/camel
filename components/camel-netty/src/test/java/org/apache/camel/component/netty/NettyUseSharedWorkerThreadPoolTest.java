/*
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
package org.apache.camel.component.netty;

import io.netty.channel.EventLoopGroup;
import org.apache.camel.BindToRegistry;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.AvailablePortFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NettyUseSharedWorkerThreadPoolTest extends BaseNettyTest {

    @RegisterExtension
    protected AvailablePortFinder.Port port2 = AvailablePortFinder.find();
    @RegisterExtension
    protected AvailablePortFinder.Port port3 = AvailablePortFinder.find();
    @BindToRegistry("sharedServerPool")
    private EventLoopGroup sharedWorkerServerGroup
            = new NettyWorkerPoolBuilder().withWorkerCount(2).withName("NettyServer").build();
    @BindToRegistry("sharedClientPool")
    private EventLoopGroup sharedWorkerClientGroup
            = new NettyWorkerPoolBuilder().withWorkerCount(3).withName("NettyClient").build();

    @Override
    protected boolean useJmx() {
        return true;
    }

    @Test
    public void testSharedThreadPool() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(30);

        for (int i = 0; i < 10; i++) {
            String reply = template.requestBody(
                    "netty:tcp://localhost:" + port.getPort() + "?textline=true&sync=true&workerGroup=#sharedClientPool",
                    "Hello World",
                    String.class);
            assertEquals("Bye World", reply);

            reply = template.requestBody(
                    "netty:tcp://localhost:" + port2.getPort() + "?textline=true&sync=true&workerGroup=#sharedClientPool",
                    "Hello Camel",
                    String.class);
            assertEquals("Hi Camel", reply);

            reply = template.requestBody(
                    "netty:tcp://localhost:" + port3.getPort() + "?textline=true&sync=true&workerGroup=#sharedClientPool",
                    "Hello Claus",
                    String.class);
            assertEquals("Hej Claus", reply);
        }

        assertMockEndpointsSatisfied();

        sharedWorkerServerGroup.shutdownGracefully().sync().await();
        sharedWorkerClientGroup.shutdownGracefully().sync().await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {

                from("netty:tcp://localhost:" + port.getPort()
                     + "?textline=true&sync=true&workerGroup=#sharedServerPool&usingExecutorService=false")
                             .validate(body().isInstanceOf(String.class)).to("log:result").to("mock:result")
                             .transform(body().regexReplaceAll("Hello", "Bye"));

                from("netty:tcp://localhost:" + port2.getPort()
                     + "?textline=true&sync=true&workerGroup=#sharedServerPool&usingExecutorService=false")
                             .validate(body().isInstanceOf(String.class)).to("log:result").to("mock:result")
                             .transform(body().regexReplaceAll("Hello", "Hi"));

                from("netty:tcp://localhost:" + port3.getPort()
                     + "?textline=true&sync=true&workerGroup=#sharedServerPool&usingExecutorService=false")
                             .validate(body().isInstanceOf(String.class)).to("log:result").to("mock:result")
                             .transform(body().regexReplaceAll("Hello", "Hej"));
            }
        };
    }
}
