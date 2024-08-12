package cloud.popples.buildweb.spittr.repository;

import cloud.popples.buildweb.spittr.pojo.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

}
