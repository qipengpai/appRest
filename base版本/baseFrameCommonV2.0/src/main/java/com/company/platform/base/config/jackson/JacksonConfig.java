package com.company.platform.base.config.jackson;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @ClassName: JacksonConfig
 * @Description: TODO(过滤掉返回的null字段)
 * @author luyuchi
 * @date 2018年3月21日 下午5:37:51
 * 
 */
@Configuration
public class JacksonConfig {
	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();

		// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
		// Include.Include.ALWAYS 默认
		// Include.NON_DEFAULT 属性为默认值不序列化
		// Include.NON_EMPTY 属性为 空（""） 或者为 NULL
		// 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
		// Include.NON_NULL 属性为NULL 不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		// 字段保留，将null值转为""
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
					throws IOException, JsonProcessingException {
				jsonGenerator.writeString("");
			}
		});
		return objectMapper;
	}
}
