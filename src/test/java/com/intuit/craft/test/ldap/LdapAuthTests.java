
package com.intuit.craft.test.ldap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={LdapAuthTests.class})
@AutoConfigureMockMvc
public class LdapAuthTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValid() throws Exception {
        FormLoginRequestBuilder login = formLogin().user("rakesh").password("abcd123");
        mockMvc.perform(login).andExpect(authenticated().withUsername("rakesh"));
    }

    @Test
    public void loginWithInvalidUser() throws Exception {
        FormLoginRequestBuilder login = formLogin().user("wrong").password("badpassword");
        mockMvc.perform(login).andExpect(unauthenticated());
    }
}

