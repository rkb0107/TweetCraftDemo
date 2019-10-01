package com.intuit.craft.ldap;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

/**
 * This is the spring security adapter class to implement the LDAP authentication
 */

@SuppressWarnings("deprecation")
@Configuration
public class LdapSecurityConfig extends WebSecurityConfigurerAdapter {

	
	/** Allowing the Post/Put operation to allow few test cases to escape from LDAP auth 
	 but this not recomonded instead we should use anyRequest() without ignoring
	 **/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/follow", "/tweet", "/user", "/h2"); //anyRequest():incase no ignore
	}

	/** Below method configuring the LDAP Authentication for the incoming user.
	 * todo: All config can be read from application.properties but now just hardcoded.
	 **/
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
				.url("ldap://localhost:8389/dc=springframework,dc=org").and().passwordCompare()
				.passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute("userPassword");
	}

}
