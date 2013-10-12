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
package au.id.hazelwood.sos.web.controller.framework;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@FixMethodOrder(MethodSorters.JVM)
public class GlobalExceptionHandlerUnitTest
{
    private GlobalExceptionHandler handler;

    @Before
    public void setUp() throws Exception
    {
        handler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleNotFound() throws Exception
    {
        WebRequest webRequest = mock(WebRequest.class);

        ResponseEntity<Object> responseEntity = handler.handleNotFound(new NoSuchMethodException("Entity not found"), webRequest);

        assertResponseEntity(responseEntity, HttpStatus.NOT_FOUND, "Entity not found");
        verifyZeroInteractions(webRequest);
    }

    @Test
    public void testHandleIllegalArgument() throws Exception
    {
        WebRequest webRequest = mock(WebRequest.class);

        ResponseEntity<Object> responseEntity = handler.handleIllegalArgument(new IllegalArgumentException("Illegal argument"), webRequest);

        assertResponseEntity(responseEntity, HttpStatus.BAD_REQUEST, "Illegal argument");
        verifyZeroInteractions(webRequest);
    }

    @Test
    public void testHandleAllUnknown() throws Exception
    {
        WebRequest webRequest = mock(WebRequest.class);
        RuntimeException ex = new RuntimeException("Runtime exception");

        ResponseEntity<Object> responseEntity = handler.handleAllUnknown(ex, webRequest);

        assertResponseEntity(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR, "Runtime exception");
        verify(webRequest).setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        verifyNoMoreInteractions(webRequest);
    }

    @SuppressWarnings("unchecked")
    private void assertResponseEntity(ResponseEntity<Object> responseEntity, HttpStatus status, String message)
    {
        assertThat(responseEntity.getStatusCode(), is(status));
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertThat(body, hasEntry("status", Object.class.cast(status.value())));
        assertThat(body, hasEntry("message", Object.class.cast(message)));
    }
}
