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

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplUnitTest
{
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserToUserDtoFunction userToUserDtoFunction;
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        userService = new UserServiceImpl(userRepository, userToUserDtoFunction);
    }

    @Test
    public void testFindAllDelegatesToUserRepository()
    {
        when(userRepository.findAll()).thenReturn(Collections.<User>emptyList());

        List<UserDto> dtos = userService.findAllUsers();

        assertThat(dtos.isEmpty(), is(true));
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(userToUserDtoFunction);
    }

    @Test
    public void testFindAll()
    {
        User userOne = mock(User.class);
        User userTwo = mock(User.class);
        UserDto userDtoOne = mock(UserDto.class);
        UserDto userDtoTwo = mock(UserDto.class);

        when(userRepository.findAll()).thenReturn(Arrays.asList(userOne, userTwo));
        when(userToUserDtoFunction.apply(userOne)).thenReturn(userDtoOne);
        when(userToUserDtoFunction.apply(userTwo)).thenReturn(userDtoTwo);

        List<UserDto> dtos = userService.findAllUsers();

        assertThat(dtos.size(), is(2));
        assertThat(dtos, contains(userDtoOne, userDtoTwo));
        verify(userRepository).findAll();
        verify(userToUserDtoFunction).apply(userOne);
        verify(userToUserDtoFunction).apply(userTwo);
        verifyNoMoreInteractions(userRepository, userToUserDtoFunction);
    }

    @Test
    public void testFindUserByEmailDelegatesToUserRepository()
    {
        String email = "test@mail.com";
        when(userRepository.findOneByEmail(email)).thenReturn(null);

        UserDto dto = userService.findUserByEmail(email);

        assertThat(dto, nullValue());
        verify(userRepository).findOneByEmail(email);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(userToUserDtoFunction);
    }

    @Test
    public void testFindUserByEmail()
    {
        String email = "test@mail.com";
        User user = mock(User.class);
        UserDto userDto = mock(UserDto.class);

        when(userRepository.findOneByEmail(email)).thenReturn(user);
        when(userToUserDtoFunction.apply(user)).thenReturn(userDto);

        UserDto dto = userService.findUserByEmail(email);

        assertThat(dto, is(userDto));
        verify(userRepository).findOneByEmail(email);
        verify(userToUserDtoFunction).apply(user);
        verifyNoMoreInteractions(userRepository, userToUserDtoFunction);
    }
}
