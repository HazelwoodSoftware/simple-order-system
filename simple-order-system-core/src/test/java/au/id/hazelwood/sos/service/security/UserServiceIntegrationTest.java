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
package au.id.hazelwood.sos.service.security;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context-test-service.xml"})
@TransactionConfiguration
@Transactional
public class UserServiceIntegrationTest
{
    @Resource
    private UserService userService;

    @Test
    public void testFindAllUsers() throws Exception
    {
        assertThat(userService.findAllUsers().size(), is(2));
    }

    @Test
    public void testFindUserByEmail() throws Exception
    {
        assertThat(userService.findUserByEmail(""), nullValue());
        assertThat(userService.findUserByEmail(null), nullValue());
        assertThat(userService.findUserByEmail("admin@hazelwood.id.au"), hasProperty("email", equalTo("admin@hazelwood.id.au")));
        assertThat(userService.findUserByEmail("ricky@hazelwood.id.au"), hasProperty("email", equalTo("ricky@hazelwood.id.au")));

    }
}
