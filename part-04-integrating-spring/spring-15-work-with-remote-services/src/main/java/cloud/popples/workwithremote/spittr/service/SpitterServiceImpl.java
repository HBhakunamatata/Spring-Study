package cloud.popples.workwithremote.spittr.service;

import cloud.popples.workwithremote.spittr.domain.Spitter;

import java.util.List;

public class SpitterServiceImpl implements SpitterService {
    @Override
    public Spitter getSpitterById(long id) {
        System.out.println("get param " + id);
        Spitter spitter =
                new Spitter(1L, "username", "password", "fullname", "email", true);
        return spitter;
    }

    @Override
    public void saveSpitters(List<Spitter> spitterList) {
        System.out.println(spitterList);
    }
}
