package com.nevex.ws;

import com.nevex.config.EmployeeConnectProperties;
import com.nevex.model.LdapUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import java.util.List;

/**
 * Created by mcunningham on 8/3/2017.
 */
@RestController
public class TestController {

    private final EmployeeConnectProperties.LdapProperties ldapProperties;

    @Autowired
    TestController(EmployeeConnectProperties properties) {
        ldapProperties = properties.getLdap();
    }

//    @PostConstruct
//    void init() throws Exception {
//        getAllEmployees();
//    }

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEmployees() throws NamingException {

        LdapContextSource cs = new LdapContextSource();
        cs.setUrls(ldapProperties.getUrls().split(" "));
        cs.setBase(ldapProperties.getBaseDn());
        cs.setUserDn(ldapProperties.getSearchUserDn());
        cs.setPassword(ldapProperties.getSearchUserPassword());
        cs.afterPropertiesSet();

        SpringSecurityLdapTemplate tm = new SpringSecurityLdapTemplate(cs);

        SearchControls searchControls = new SearchControls();
        searchControls.setCountLimit(5000);
        searchControls.setTimeLimit(10000);

        LdapQuery ldapQuery = LdapQueryBuilder.query()
                .base(ldapProperties.getSearchUserBaseDn())
                .searchScope(SearchScope.SUBTREE)
                .countLimit(1000)
                .timeLimit(10000)
                .attributes("userprincipalname", "cn")
                .filter("(objectClass=user)");

        List<LdapUserDto> results = tm.search(ldapQuery, new AttributesMapper<LdapUserDto>() {
            @Override
            public LdapUserDto mapFromAttributes(Attributes attributes) throws NamingException {
                return new LdapUserDto(
                        // TODO: safety checks
                        attributes.get("userprincipalname").get(0).toString(),
                        attributes.get("cn").get(0).toString());
            }
        });
        System.out.println("Number of employees obtained - "+results.size());
        return ResponseEntity.ok(results);
    }



}
