package com.programmaniaks.training.unittest.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.programmaniaks.training.unittest.entity.Article;

@Configuration
public class WebConfiguration {

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new ArticleJacksonModule());
		return objectMapper;
	}

	public static class ArticleKeyDeserializer extends KeyDeserializer {
		@Override
		public Article deserializeKey(final String key,
				final DeserializationContext ctxt) throws IOException,
				JsonProcessingException {
			String value = key.substring(8, key.length() - 1);
			String[] splits = value.split(",");
			
			Article result = new Article();
			result.setId(Long.valueOf(parseValue(splits[0])));
			result.setName(parseValue(splits[1]));
			result.setQuantity(Integer.valueOf(parseValue(splits[2])));
			result.setPrice(Double.valueOf(parseValue(splits[3])));
			result.setDescription(parseValue(splits[4]));
			return result;
		}
		
		public String parseValue(String string) {
			if (string == null || string.trim().equals("null")) {
				return null;
			}
			return string.trim();
		}
	}

	public static class ArticleJacksonModule extends SimpleModule {
		private static final long serialVersionUID = 4944350028970990669L;

		public ArticleJacksonModule() {
			addKeyDeserializer(Article.class, new ArticleKeyDeserializer());
		}
	}
}
