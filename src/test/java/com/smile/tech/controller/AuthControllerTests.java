package com.smile.tech.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.tech.model.Users;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AuthControllerTests {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Autowired
	ObjectMapper mapper;

	@Before
	@Test
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

	}

	@WithMockUser(username = "admin", password = "123456789", roles = "ADMIN")
	@Test
	public void savePerson() throws Exception {
		Users user = new Users();
		user.setUsername("user1");
		user.setPassword("123456789");

		String jsonreq = mapper.writeValueAsString(user);

		MvcResult resul = mockMvc
				.perform(post("/auth/user/signup").content(jsonreq).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, resul.getResponse().getStatus());
	}

	@WithMockUser(username = "admin", password = "123456789", roles = "ADMIN")
	@Test
	public void getAllUsers() throws Exception {

		MvcResult resul = mockMvc.perform(get("/api/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, resul.getResponse().getStatus());
	}

	@Test
	public void signinPerson() throws Exception {
		Users user = new Users();
		user.setUsername("user1");
		user.setPassword("123456789");

		String jsonreq = mapper.writeValueAsString(user);

		MvcResult resul = mockMvc.perform(post("/auth/signin").content(jsonreq).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, resul.getResponse().getStatus());
	}

}
