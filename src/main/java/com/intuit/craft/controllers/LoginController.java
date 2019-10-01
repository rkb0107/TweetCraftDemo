package com.intuit.craft.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class LoginController {

	/** Generic root api to validate LDAP authentication of the login user status**/
    @GetMapping("/")
    public String index() {
        return "Successfully login!";
    }
}
