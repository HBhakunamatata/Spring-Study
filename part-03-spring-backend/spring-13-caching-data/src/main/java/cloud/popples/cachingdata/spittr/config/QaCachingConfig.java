package cloud.popples.cachingdata.spittr.config;

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
