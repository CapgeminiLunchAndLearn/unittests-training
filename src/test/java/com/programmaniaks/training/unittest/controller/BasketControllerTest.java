package com.programmaniaks.training.unittest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.nio.charset.Charset;

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

import com.google.common.collect.Maps;
import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;
import com.programmaniaks.training.unittest.service.BasketService;
import com.programmaniaks.training.unittest.utils.JsonParser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-context.xml")
@WebAppConfiguration
public class BasketControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
												MediaType.APPLICATION_JSON.getSubtype(),
												Charset.forName("utf8"));
	
	@Autowired
	@InjectMocks
	private BasketController basketController;
	
	@Mock
	private BasketService basketService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void computeSumTest() throws Exception{
		Basket basket = new Basket();
		basket.setContent(Maps.newHashMap());
		Article article = new Article();
		article.setId(3L);
		article.setName("Produit A");
		article.setPrice(20.0);
		article.setQuantity(40);
		basket.getContent().put(article, 3);
		
		when(basketService.computeCheckOut(basket)).thenReturn(new BigDecimal(60));
		
		MvcResult result = mockMvc.perform(post("/basket/sum")
				.contentType(contentType)
				.content(JsonParser.toJson(basket)))
				.andReturn();
		assertEquals("60", result.getResponse().getContentAsString());
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
