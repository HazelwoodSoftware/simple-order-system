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

import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Function to convert {@link User} into {@link UserDto}.
 *
 * @author Ricky Hazelwood
 */
@Component
public class UserToUserDtoFunction implements Function<User, UserDto>
{
    @Override
    public UserDto apply(User user)
    {
        return user == null ? null : transform(user);
    }

    private UserDto transform(User user)
    {
        UserDto dto;
        dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
