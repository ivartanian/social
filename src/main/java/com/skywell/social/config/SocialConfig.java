/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skywell.social.config;

import com.skywell.social.custom.RestOperationsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.oauth2.GenericOAuth2ConnectionFactory;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * Spring Social Configuration.
 * This configuration is demonstrating the use of the simplified Spring Social configuration options from Spring Social 1.1.
 *
 * @author Craig Walls
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    private final DataSource dataSource;
    private final ConnectionSignUp connectionSignUp;

    @Autowired
    public SocialConfig(DataSource dataSource, ConnectionSignUp connectionSignUp) {
        this.dataSource = dataSource;
        this.connectionSignUp = connectionSignUp;
    }
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//        cfConfig.addConnectionFactory(new FacebookConnectionFactory("696436577172062", "700900186591bc61cb595b57ea86aefb"));
        cfConfig.addConnectionFactory(
                new GenericOAuth2ConnectionFactory(
                        "facebook",
                        "696436577172062",
                        "700900186591bc61cb595b57ea86aefb",
                        "https://www.facebook.com/v2.5/dialog/oauth",
                        null,
                        "https://graph.facebook.com/v2.5/oauth/access_token",
                        true,
                        TokenStrategy.AUTHORIZATION_HEADER,
                        new RestOperationsAdapter()));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(connectionSignUp);
        return repository;
    }

}
