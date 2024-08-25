package cloud.popples.databaseandjdbc.repository;

import cloud.popples.databaseandjdbc.pojo.Spitter;

public interface SpitterRepository {

    void addSpitter(Spitter spitter);

    Spitter findOne(long id);

    void updateSpitter(Spitter spitter);

}
