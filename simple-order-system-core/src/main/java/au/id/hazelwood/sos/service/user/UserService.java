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
package au.id.hazelwood.sos.service.user;

import au.id.hazelwood.sos.dto.user.UserDto;

import java.util.List;

/**
 * Service to find, create, update Users.
 *
 * @author Ricky Hazelwood
 */
public interface UserService
{
    /**
     * Returns all {@link UserDto}.
     *
     * @return all {@link UserDto}
     */
    List<UserDto> findAllUsers();

    /**
     * Retrieves {@link UserDto} by id.
     *
     * @param id {@link UserDto}'s id
     * @return {@link UserDto} with the given id or {@literal null} if none found.
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    UserDto findUserById(Long id);

    /**
     * Retrieves {@link UserDto} by email address.
     *
     * @param email {@link UserDto}'s email address
     * @return {@link UserDto} with the given email address or {@literal null} if none found.
     */
    UserDto findUserByEmail(String email);
}
