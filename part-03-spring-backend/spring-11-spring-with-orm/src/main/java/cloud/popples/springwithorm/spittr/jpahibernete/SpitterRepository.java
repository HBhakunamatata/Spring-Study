package cloud.popples.springwithorm.spittr.jpahibernete;

import cloud.popples.springwithorm.spittr.domain.Spitter;

public interface SpitterRepository {

    void addSpitter(Spitter spitter);

    Spitter getSpitterById(long id);

    void saveSpitter(Spitter spitter);

}
