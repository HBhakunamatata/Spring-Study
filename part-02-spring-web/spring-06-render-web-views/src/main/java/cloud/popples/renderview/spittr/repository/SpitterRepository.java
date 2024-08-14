package cloud.popples.renderview.spittr.repository;

import cloud.popples.renderview.spittr.pojo.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

}
