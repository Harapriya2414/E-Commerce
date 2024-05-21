package hpecomm.ecommerce.Configurations;

import hpecomm.ecommerce.DTO.FakeStoreProductDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RedisTemplate<Long, FakeStoreProductDto> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Long, FakeStoreProductDto> redisTemplate =new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, List<FakeStoreProductDto>> categoryRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, List<FakeStoreProductDto>> redisTemplate =new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Long.class));
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
