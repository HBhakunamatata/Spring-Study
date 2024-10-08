package cloud.popples.renderview.spittr.repository;

import cloud.popples.renderview.spittr.pojo.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);
}
