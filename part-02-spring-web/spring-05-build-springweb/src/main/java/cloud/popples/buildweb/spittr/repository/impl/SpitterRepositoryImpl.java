package cloud.popples.buildweb.spittr.repository.impl;

import cloud.popples.buildweb.spittr.pojo.Spitter;
import cloud.popples.buildweb.spittr.repository.SpitterRepository;
import org.springframework.stereotype.Component;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {
    @Override
    public Spitter save(Spitter spitter) {
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }
}
