package com.programmaniaks.training.unittest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

import com.programmaniaks.training.unittest.entity.User;
import com.programmaniaks.training.unittest.service.UserService;
import com.programmaniaks.training.unittest.utils.JsonParser;

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
	public void userCreate() throws Exception {
		User userExpected = new User();
		userExpected.setId((long) 1);
		userExpected.setName("Toto");
		userExpected.setUsername("Tata");
		userExpected.setPassword("123");
		userExpected.setDateOfBirth(new Date());
		
		MvcResult result = mockMvc.perform(put("/user/")
					.contentType(contentType)
					.content(JsonParser.toJson(userExpected)))
					.andReturn();
		
		User userActual = JsonParser.toObject(result.getResponse().getContentAsString(), User.class);
		assertEquals(userExpected, userActual);
		verify(userService, times(1)).create(userExpected);
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
