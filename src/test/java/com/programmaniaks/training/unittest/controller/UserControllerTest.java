package com.programmaniaks.training.unittest.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmaniaks.training.unittest.entity.User;
import com.programmaniaks.training.unittest.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-context.xml")
@WebAppConfiguration
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
												MediaType.APPLICATION_JSON.getSubtype(),
												Charset.forName("utf8"));
	
	@Autowired
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void userCreate() {
		User user = new User();
		user.setId((long) 1);
		user.setName("Toto");
		user.setUsername("Tata");
		user.setPassword("123");
		user.setDateOfBirth(new Date());
		
		
		try {
			MvcResult result = mockMvc.perform(put("/user/")
					.contentType(contentType)
					.content(json(user)))
					.andReturn();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		verify(userService, times(1)).create(user);
	}
	
	
	protected String json(Object o) throws JsonProcessingException {
		ObjectMapper test = new ObjectMapper();
		return test.writeValueAsString(o);
    }

	public MediaType getContentType() {
		return contentType;
	}

	public void setContentType(MediaType contentType) {
		this.contentType = contentType;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}
