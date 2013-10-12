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
package au.id.hazelwood.sos.web.controller.user;

import au.id.hazelwood.sos.dto.user.UserDto;
import au.id.hazelwood.sos.service.user.UserService;
import au.id.hazelwood.sos.web.exception.NotFoundException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(MockitoJUnitRunner.class)
public class UsersControllerUnitTest
{
    @Mock
    private UserService userService;
    private UsersController usersController;

    @Before
    public void setUp() throws Exception
    {
        usersController = new UsersController(userService);
    }

    @Test
    public void testFindAllUsersDelegatesToUserService() throws Exception
    {
        when(userService.findAllUsers()).thenReturn(Collections.<UserDto>emptyList());

        List<UserDto> dtos = usersController.findAllUsers();

        assertThat(dtos.isEmpty(), is(true));
        verify(userService).findAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testFindUserDelegatesToUserService() throws Exception
    {
        Long userId = 10L;
        UserDto userDto = mock(UserDto.class);

        when(userService.findUserById(userId)).thenReturn(userDto);

        UserDto actual = usersController.findUser(userId);

        assertThat(actual, is(userDto));
        verify(userService).findUserById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test(expected = NotFoundException.class)
    public void testFindUserThrowsNotFoundExceptionWhenUserMissing() throws Exception
    {
        Long userId = 10L;

        when(userService.findUserById(userId)).thenReturn(null);

        try
        {
            usersController.findUser(userId);
        }
        finally
        {
            verify(userService).findUserById(userId);
            verifyNoMoreInteractions(userService);
        }
    }
}
