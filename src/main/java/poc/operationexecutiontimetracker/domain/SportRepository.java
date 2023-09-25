package poc.operationexecutiontimetracker.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "sport", keyGenerator = "customKeyGenerator")
public interface SportRepository extends CrudRepository<Sport, Long> {

    @Cacheable
    Sport findByName(String name);

    @Override
    @Cacheable
    Optional<Sport> findById(Long id);
}
