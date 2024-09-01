# Ch13 Caching Data in Spring

- Enabling declarative caching
- Caching with Ehcache, Redis
- Annotation-oriented caching

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>${spring-version}</version>
</dependency>

<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.7.4</version>
</dependency>

<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache-jcache</artifactId>
    <version>1.4.0-beta1</version>
</dependency>

<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.5.2</version>
</dependency>

<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>

<dependency>
    <groupId>javax.cache</groupId>
    <artifactId>cache-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 13.1 Enabling cache support

Configuring a CacheManager

```java
@Configuration
@EnableCaching
@Profile("dev")
public class DevCachingConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

}

import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
@Configuration
@EnableCaching
@Profile("qa")
public class QaCachingConfig {

    /**
     * To be clear, Ehcache’s CacheManager is being injected into Spring’s
     * EhCacheCacheManager (which implements Spring’s CacheManager implementation).
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("cloud/popples/cachingdata/ehcache.xml"));
        return factoryBean;
    }

}

@Configuration
@EnableCaching
@Profile("prod")
public class RedisCachingConfig {

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("localhost");
        factory.setPort(6379);
        return factory;
    }

}
```

## 13.2 Annotating methods for caching

Difference between unless and condition

This is possible and useful because unless doesn’t start doing its job until a value is returned from the cached method. On the other hand, condition has the job of disabling caching on the method. Therefore, it can’t wait until the method has completed to decide if it needs to shut down caching. This means its expression must be evaluated on the way into the method and that you
can’t refer to the return value with #result.

```java
public interface SpittleRepository extends JpaRepository<Spittle, Long> {

    @Cacheable(value = "spittleCache", key = "#result.id",
                unless = "#result.message.contains('NoCache')",
                condition = "#id >= 10"
    )
    Spittle findOne(long id);

    @CachePut(value = "spittleCache", key = "#result.id")
    Spittle save(Spittle spittle);


    @CacheEvict(value = "spittleCache")
    void delete(long id);

}
```