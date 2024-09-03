package cloud.popples.securingmethods.spittr.service;

import cloud.popples.securingmethods.spittr.domain.Spittle;

import java.util.Collections;
import java.util.List;

public interface SpittleService {

    void addSpittle(Spittle spittle);

    default Spittle getSpittleById(Long id) {
        return null;
    }

    default List<Spittle> getAllSpittles() {
        return Collections.emptyList();
    }

    default void deleteSpittles(List<Spittle> spittles) {
        return;
    }

    default int updateSpittle(List<Spittle> spittle) {
        return Integer.MAX_VALUE;
    }

}
