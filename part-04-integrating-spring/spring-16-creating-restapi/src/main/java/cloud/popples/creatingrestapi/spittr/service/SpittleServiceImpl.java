package cloud.popples.creatingrestapi.spittr.service;

import cloud.popples.creatingrestapi.spittr.domain.Spittle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpittleServiceImpl implements SpittleService{
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Spittle spittle = new Spittle((long)i, "fsdfs", new Date(), 12.0, 12.0);
            spittles.add(spittle);
        }
        return spittles;
    }

    @Override
    public Spittle saveSpittle(Spittle spittle) {
        spittle.setId(100L);
        System.out.println(spittle);
        return spittle;
    }

    @Override
    public Spittle findSpittleById(long id) {
        if (id == 10) {
            return null;
        }
        Spittle spittle = new Spittle(id, "fsdfs", new Date(), 12.0, 12.0);
        return spittle;
    }
}
