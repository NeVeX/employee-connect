package com.prosper.mark.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by mcunningham on 8/3/2017.
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "employee-connect")
class EmployeeConnectProperties {

    @Valid
    @NotNull(message = "No ldap properties are set")
    private LdapProperties ldap;

    public static class LdapProperties {

        @NotBlank(message = "No ldap urls were provided")
        private String urls;
        @NotBlank(message = "No ldap rootDn was provided")
        private String rootDn;
        @NotBlank(message = "No ldap domain was provided")
        private String domain;

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }

        public String getRootDn() {
            return rootDn;
        }

        public void setRootDn(String rootDn) {
            this.rootDn = rootDn;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        @Override
        public String toString() {
            return "LdapProperties{" +
                    "urls='" + urls + '\'' +
                    ", rootDn='" + rootDn + '\'' +
                    ", domain='" + domain + '\'' +
                    '}';
        }
    }

    public LdapProperties getLdap() {
        return ldap;
    }

    public void setLdap(LdapProperties ldap) {
        this.ldap = ldap;
    }

    @Override
    public String toString() {
        return "EmployeeConnectProperties{" +
                "ldap=" + ldap +
                '}';
    }
}
