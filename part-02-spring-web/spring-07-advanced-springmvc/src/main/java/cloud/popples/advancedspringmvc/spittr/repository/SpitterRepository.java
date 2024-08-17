package cloud.popples.advancedspringmvc.spittr.repository;

import cloud.popples.advancedspringmvc.spittr.pojo.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

}
