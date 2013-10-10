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
import au.id.hazelwood.sos.repository.security.UserRepository;
import au.id.hazelwood.sos.service.security.UserService;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} that uses {@link UserRepository}.
 *
 * @author Ricky Hazelwood
 */
@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final UserToUserDtoFunction userToUserDtoFunction;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserToUserDtoFunction userToUserDtoFunction)
    {
        this.userRepository = userRepository;
        this.userToUserDtoFunction = userToUserDtoFunction;
    }

    @Override
    public List<UserDto> findAllUsers()
    {
        return Lists.newArrayList(Lists.transform(userRepository.findAll(), userToUserDtoFunction));
    }

    @Override
    public UserDto findUserByEmail(String email)
    {
        User user = userRepository.findOneByEmail(email);
        return user == null ? null : userToUserDtoFunction.apply(user);
    }
}