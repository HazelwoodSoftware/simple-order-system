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
package au.id.hazelwood.sos.repository.security;

import au.id.hazelwood.sos.model.security.User;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context-service.xml"})
@TransactionConfiguration
@Transactional
public class UserRepositoryIntegrationTest
{
    @Resource
    private UserRepository userRepository;

    @Test
    public void testDefaultRepositoryState() throws Exception
    {
        assertThat(userRepository.count(), is(0L));
        assertThat(userRepository.findAll().isEmpty(), is(true));
    }

    @Test
    public void testCreateAndDelete() throws Exception
    {
        User one = new User("one@hazelwood.id.au", "password", "User", "One");
        User two = new User("two@hazelwood.id.au", "password", "User", "Two");
        User three = new User("three@hazelwood.id.au", "password", "User", "Three");
        userRepository.save(one);
        assertThat(userRepository.count(), is(1L));
        assertThat(userRepository.exists(one.getId()), is(true));

        userRepository.save(Arrays.asList(two, three));
        assertThat(userRepository.count(), is(3L));

        userRepository.delete(one);
        assertThat(userRepository.count(), is(2L));
        assertThat(userRepository.exists(one.getId()), is(false));

        userRepository.delete(Arrays.asList(two, three));
        assertThat(userRepository.count(), is(0L));
    }

    @Test
    public void testFindOneByEmail() throws Exception
    {
        User one = new User("one@hazelwood.id.au", "password", "User", "One");
        User two = new User("two@hazelwood.id.au", "password", "User", "Two");

        userRepository.save(Arrays.asList(one, two));
        assertThat(userRepository.findOneByEmail("one@hazelwood.id.au"), is(one));
        assertThat(userRepository.findOneByEmail("two@hazelwood.id.au"), is(two));
        assertThat(userRepository.findOneByEmail(null), nullValue());
        assertThat(userRepository.findOneByEmail(""), nullValue());
    }
}
