package cloud.popples.securingweb.spittr.repository;

import cloud.popples.securingweb.spittr.pojo.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

}
