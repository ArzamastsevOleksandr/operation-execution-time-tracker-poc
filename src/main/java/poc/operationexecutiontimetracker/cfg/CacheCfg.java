package poc.operationexecutiontimetracker.cfg;

import okhttp3.OkHttpClient;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
class CacheCfg {

    @Bean
    CustomKeyGenerator customKeyGenerator() {
        return new CustomKeyGenerator();
    }

    @Bean
    CacheManager cacheManager(List<ConcurrentMapCache> caches) {
        var cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    ConcurrentMapCache sportCache() {
        return new ConcurrentMapCache("sport");
    }

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
