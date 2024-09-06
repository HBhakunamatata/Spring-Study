package cloud.popples.creatingrestapi.spittr.service;

import cloud.popples.creatingrestapi.spittr.domain.Spittle;

import java.util.List;

public interface SpittleService {

    List<Spittle> findSpittles(long max, int count);

    Spittle saveSpittle(Spittle spittle);

    Spittle findSpittleById(long id);

}
