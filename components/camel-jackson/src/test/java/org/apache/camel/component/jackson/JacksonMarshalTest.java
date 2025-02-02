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
package org.apache.camel.component.jackson;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonMarshalTest extends CamelTestSupport {

    @Test
    public void testMarshalAndUnmarshalMap() throws Exception {
        Map<String, Object> in = new HashMap<>();
        in.put("name", "Camel");

        MockEndpoint mock = getMockEndpoint("mock:reverse");
        mock.expectedMessageCount(1);
        mock.message(0).body().isInstanceOf(Map.class);
        mock.message(0).body().isEqualTo(in);

        Object marshalled = template.requestBody("direct:in", in);
        String marshalledAsString = context.getTypeConverter().convertTo(String.class, marshalled);
        assertEquals("{\"name\":\"Camel\"}", marshalledAsString);

        template.sendBody("direct:back", marshalled);

        mock.assertIsSatisfied();
    }

    @Test
    public void testMarshalAndUnmarshalMapWithPrettyPrint() throws Exception {
        Map<String, Object> in = new HashMap<>();
        in.put("name", "Camel");

        MockEndpoint mock = getMockEndpoint("mock:reverse");
        mock.expectedMessageCount(1);
        mock.message(0).body().isInstanceOf(Map.class);
        mock.message(0).body().isEqualTo(in);

        Object marshalled = template.requestBody("direct:inPretty", in);
        String marshalledAsString = context.getTypeConverter().convertTo(String.class, marshalled);
        String expected = String.format("{%s  \"name\" : \"Camel\"%s}", System.lineSeparator(), System.lineSeparator());
        assertEquals(expected, marshalledAsString);

        template.sendBody("direct:backPretty", marshalled);

        mock.assertIsSatisfied();
    }

    @Test
    public void testMarshalAndUnmarshalPojo() throws Exception {
        TestPojo in = new TestPojo();
        in.setName("Camel");

        MockEndpoint mock = getMockEndpoint("mock:reversePojo");
        mock.expectedMessageCount(1);
        mock.message(0).body().isInstanceOf(TestPojo.class);
        mock.message(0).body().isEqualTo(in);

        Object marshalled = template.requestBody("direct:inPojo", in);
        String marshalledAsString = context.getTypeConverter().convertTo(String.class, marshalled);
        assertEquals("{\"name\":\"Camel\"}", marshalledAsString);

        template.sendBody("direct:backPojo", marshalled);

        mock.assertIsSatisfied();
    }

    @Test
    public void testUnmarshalNullBody() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:nullBody");
        mock.expectedMessageCount(1);
        mock.message(0).body().isNull();

        template.sendBody("direct:nullBody", null);
        mock.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                JacksonDataFormat format = new JacksonDataFormat();

                from("direct:in").marshal(format);
                from("direct:back").unmarshal(format).to("mock:reverse");

                JacksonDataFormat prettyPrintDataFormat = new JacksonDataFormat();
                prettyPrintDataFormat.setPrettyPrint(true);

                from("direct:inPretty").marshal(prettyPrintDataFormat);
                from("direct:backPretty").unmarshal(prettyPrintDataFormat).to("mock:reverse");

                JacksonDataFormat formatPojo = new JacksonDataFormat(TestPojo.class);

                from("direct:inPojo").marshal(formatPojo);
                from("direct:backPojo").unmarshal(formatPojo).to("mock:reversePojo");

                JacksonDataFormat allowNullBodyDataFormat = new JacksonDataFormat();
                from("direct:nullBody").unmarshal(allowNullBodyDataFormat, true).to("mock:nullBody");
            }
        };
    }

}
