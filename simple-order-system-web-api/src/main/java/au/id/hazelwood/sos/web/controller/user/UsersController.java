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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/users")
public class UsersController
{
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService)
    {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserDto> findAllUsers()
    {
        return userService.findAllUsers();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto findUser(@PathVariable("id") Long id) throws NotFoundException
    {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null)
        {
            throw new NotFoundException(String.format("User with id %d not found", id));
        }
        return userDto;
    }
}
