package cloud.popples.renderview.spittr.repository.impl;

import cloud.popples.renderview.spittr.pojo.Spittle;
import cloud.popples.renderview.spittr.repository.SpittleRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return Collections.emptyList();
    }

    @Override
    public Spittle findOne(long id) {
        return null;
    }
}
