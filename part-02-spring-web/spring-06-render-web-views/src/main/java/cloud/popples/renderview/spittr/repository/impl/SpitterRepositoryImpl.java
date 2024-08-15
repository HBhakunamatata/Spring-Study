package cloud.popples.renderview.spittr.repository.impl;

import cloud.popples.renderview.spittr.pojo.Spitter;
import cloud.popples.renderview.spittr.repository.SpitterRepository;
import org.springframework.stereotype.Component;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {
    @Override
    public Spitter save(Spitter spitter) {
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = new Spitter();
        return spitter;
    }
}
