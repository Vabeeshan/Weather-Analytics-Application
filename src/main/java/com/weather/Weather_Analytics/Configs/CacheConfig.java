/*
In Spring Boot, a cache configuration file:

    Defines which cache manager your app will use (e.g., Caffeine, Redis, Ehcache).
    Configures cache behavior like time-to-live (TTL), maximum size, and eviction policies.
    Allows you to use annotations like @Cacheable, @CacheEvict, and @CachePut in services.

Why it’s needed in your assignment:

    You’re fetching weather data from OpenWeatherMap for multiple cities.
    API calls are rate-limited and can be slow.
    By caching responses for a few minutes, you reduce latency and API calls.
    Improves performance and avoids hitting the external API repeatedly.
 */

package com.weather.Weather_Analytics.Configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching //Enables Spring’s caching mechanism, so you can use @Cacheable in your services.
public class CacheConfig {

    // Bean to configure the Caffeine cache manager
    @Bean
    public CacheManager cachemanager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("weatherCache");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    //Bean to define cache properties
    @Bean
    public Caffeine<Object,Object> caffeineCacheBuilder(){
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(1000)
                .recordStats();

    }
}
