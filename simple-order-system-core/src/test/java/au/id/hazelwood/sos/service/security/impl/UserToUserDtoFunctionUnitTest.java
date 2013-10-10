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
package au.id.hazelwood.sos.service.security.impl;

import au.id.hazelwood.sos.dto.security.UserDto;
import au.id.hazelwood.sos.model.security.User;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@FixMethodOrder(MethodSorters.JVM)
public class UserToUserDtoFunctionUnitTest
{
    private final UserToUserDtoFunction function = new UserToUserDtoFunction();

    @Test
    public void testApplyWithNull() throws Exception
    {
        UserDto dto = function.apply(null);

        assertThat(dto, nullValue());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testApplyWithUser() throws Exception
    {
        User user = new User("test@mail.com", "password", "first", "last");
        user.setId(1001L);

        UserDto dto = function.apply(user);

        assertThat(dto, notNullValue());
        assertThat(dto.getId(), is(1001L));
        assertThat(dto.getEmail(), is("test@mail.com"));
        assertThat(dto.getFirstName(), is("first"));
        assertThat(dto.getLastName(), is("last"));
    }
}
