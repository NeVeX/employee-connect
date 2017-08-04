package com.nevex.config;

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
public class EmployeeConnectProperties {

    @Valid
    @NotNull(message = "No ldap properties are set")
    private LdapProperties ldap;

    public static class LdapProperties {

        @NotBlank(message = "No ldap urls were provided")
        private String urls;
        @NotBlank(message = "No ldap rootDn was provided")
        private String baseDn;
        @NotBlank(message = "No ldap domain was provided")
        private String domain;
        @NotBlank(message = "No ldap searchUserDn was provided")
        private String searchUserDn;
        @NotBlank(message = "No ldap searchUserPassword was provided")
        private String searchUserPassword;
        @NotBlank(message = "No ldap searchUserBaseDn was provided")
        private String searchUserBaseDn;

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }

        public String getBaseDn() {
            return baseDn;
        }

        public void setBaseDn(String baseDn) {
            this.baseDn = baseDn;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getSearchUserDn() {
            return searchUserDn;
        }

        public void setSearchUserDn(String searchUserDn) {
            this.searchUserDn = searchUserDn;
        }

        public String getSearchUserPassword() {
            return searchUserPassword;
        }

        public void setSearchUserPassword(String searchUserPassword) {
            this.searchUserPassword = searchUserPassword;
        }

        public String getSearchUserBaseDn() {
            return searchUserBaseDn;
        }

        public void setSearchUserBaseDn(String searchUserBaseDn) {
            this.searchUserBaseDn = searchUserBaseDn;
        }

        @Override
        public String toString() {
            return "LdapProperties{" +
                    "urls='" + urls + '\'' +
                    ", baseDn='" + baseDn + '\'' +
                    ", domain='" + domain + '\'' +
                    ", searchUserDn='" + searchUserDn + '\'' +
                    ", searchUserBaseDn='" + searchUserBaseDn + '\'' +
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
