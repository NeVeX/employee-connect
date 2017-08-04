package com.prosper.mark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * Created by mcunningham on 8/3/2017.
 */
@Configuration
@EnableWebSecurity
public class EmployeeConnectSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConnectSecurityConfiguration.class);

    @Valid
    @Autowired
    private EmployeeConnectProperties properties;

    @PostConstruct
    void init() {
        LOGGER.info("Security configuration completed. Properties used: [{}]", properties);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().fullyAuthenticated()
            .and()
            .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        EmployeeConnectProperties.LdapProperties ldapProperties = properties.getLdap();

        ActiveDirectoryLdapAuthenticationProvider provider=
                new ActiveDirectoryLdapAuthenticationProvider(
                        ldapProperties.getDomain(),
                        ldapProperties.getUrls(),
                        ldapProperties.getRootDn()
                );
        auth.authenticationProvider(provider);
    }

}