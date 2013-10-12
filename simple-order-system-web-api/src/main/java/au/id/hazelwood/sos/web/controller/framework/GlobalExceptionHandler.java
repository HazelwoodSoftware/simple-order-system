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

import au.id.hazelwood.sos.web.exception.NotFoundException;

import com.google.common.collect.Maps;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

/**
 * Handler for all exceptions thrown during the processing of a request.
 *
 * @author Ricky Hazelwood
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, createDefaultResponseBody(ex, HttpStatus.NOT_FOUND), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllUnknown(Exception ex, WebRequest request)
    {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        return super.handleExceptionInternal(ex, body == null ? createDefaultResponseBody(ex, status) : body, headers, status, request);
    }

    private Map<String, Object> createDefaultResponseBody(Exception ex, HttpStatus status)
    {
        Map<String, Object> body = Maps.newLinkedHashMap();
        body.put("status", status.value());
        body.put("message", ex.getMessage());
        return body;
    }
}