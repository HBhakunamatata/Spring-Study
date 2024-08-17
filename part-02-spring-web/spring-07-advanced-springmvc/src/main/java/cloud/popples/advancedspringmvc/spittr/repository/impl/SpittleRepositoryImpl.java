package cloud.popples.advancedspringmvc.spittr.repository.impl;

import cloud.popples.advancedspringmvc.spittr.exceptionhandler.SpittleDuplicateException;
import cloud.popples.advancedspringmvc.spittr.pojo.Spittle;
import cloud.popples.advancedspringmvc.spittr.repository.SpittleRepository;
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

    @Override
    public void save(Spittle spittle) {
        throw new SpittleDuplicateException();
    }
}
