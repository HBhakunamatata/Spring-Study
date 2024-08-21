package cloud.popples.securingweb.spittr.repository.impl;

import cloud.popples.securingweb.spittr.pojo.Spitter;
import cloud.popples.securingweb.spittr.repository.SpitterRepository;
import org.springframework.stereotype.Component;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {
    @Override
    public Spitter save(Spitter spitter) {
        spitter.setId(1L);
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = new Spitter(username, "123456", "", "", "");
        return spitter;
    }
}
