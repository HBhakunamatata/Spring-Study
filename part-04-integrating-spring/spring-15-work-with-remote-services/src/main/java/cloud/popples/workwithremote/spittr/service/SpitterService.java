package cloud.popples.workwithremote.spittr.service;

import cloud.popples.workwithremote.spittr.domain.Spitter;

import java.util.List;

public interface SpitterService {

    Spitter getSpitterById(long id);

    void saveSpitters(List<Spitter> spitterList);

}
