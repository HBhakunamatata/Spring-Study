package cloud.popples.cachingdata.spittr.repository;

import cloud.popples.cachingdata.spittr.domain.Spittle;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

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
