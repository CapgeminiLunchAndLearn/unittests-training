package com.programmaniaks.training.unittest.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.programmaniaks.training.unittest.Application;
import com.programmaniaks.training.unittest.entity.Article;
import com.programmaniaks.training.unittest.entity.Basket;




import com.programmaniaks.training.unittest.service.BasketService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-test-context.xml")
@WebAppConfiguration
public class BasketControllerTest {
	@Autowired
	private BasketController basketController;
	


	public HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		return mappingJackson2HttpMessageConverter;
	}

	public void setMappingJackson2HttpMessageConverter(
			HttpMessageConverter mappingJackson2HttpMessageConverter) {
		this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
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

	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
												MediaType.APPLICATION_JSON.getSubtype(),
												Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Test
	public void computeSumTest() throws Exception{
		Basket basket = new Basket();
		basket.setContent(new HashMap<Article, Integer>());
		Article article = new Article();
		article.setPrice(20.0);
		basket.getContent().put(article, 3);
		mockMvc.perform(post("/basket/sum")
				.contentType(contentType)
				.content(json(basket)));
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}