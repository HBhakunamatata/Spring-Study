package cloud.popples.advancedspringmvc.spittr.repository;

import cloud.popples.advancedspringmvc.spittr.pojo.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    void save(Spittle spittle);
}
