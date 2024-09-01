package cloud.popples.cachingdata.spittr.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

//@Configuration
//@EnableCaching
//@Profile("qa")
//public class QaCachingConfig {
//
//    /**
//     * To be clear, Ehcache’s CacheManager is being injected into Spring’s
//     * EhCacheCacheManager (which implements Spring’s CacheManager implementation).
//     */
//    @Bean
//    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
//        return new EhCacheCacheManager(cacheManager);
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehcache() {
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        factoryBean.setConfigLocation(new ClassPathResource("cloud/popples/cachingdata/ehcache.xml"));
//        return factoryBean;
//    }
//
//}
