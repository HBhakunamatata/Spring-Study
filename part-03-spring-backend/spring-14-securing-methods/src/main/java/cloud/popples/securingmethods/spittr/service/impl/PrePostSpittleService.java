package cloud.popples.securingmethods.spittr.service.impl;

import cloud.popples.securingmethods.spittr.domain.Spitter;
import cloud.popples.securingmethods.spittr.domain.Spittle;
import cloud.popples.securingmethods.spittr.service.SpittleService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrePostSpittleService implements SpittleService {
    @Override
    @PreAuthorize("hasRole('ROLE_SPITTER') " +
                    "and #spittle.message.length() < 100" +
                    "or hasRole('ROLE_ADMIN')")
    public void addSpittle(Spittle spittle) {
        System.out.println("Call addSpittle successfully");
    }

    @PostAuthorize("authentication.principal == returnObject.spitter.username")
    public Spittle getSpittleById(Long id) {
        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spittle spittle = new Spittle();
        spittle.setId(id);
        spittle.setMessage("Hello World");
        spittle.setPostedTime(new Date());
        spittle.setSpitter(spitter);
        return spittle;
    }

    @Override
    @PostFilter("filterObject.spitter.username == authentication.principal")
    public List<Spittle> getAllSpittles() {
        List<Spittle> spittles = new ArrayList<Spittle>();

        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spitter admin = new Spitter();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("password");

        for (int i = 0; i < 3; i++) {
            Spittle spittle = new Spittle();
            spittle.setId((long)i);
            spittle.setMessage("Hello World");
            spittle.setPostedTime(new Date());
            spittle.setSpitter(spitter);

            spittles.add(spittle);

            Spittle spittle2 = new Spittle();
            spittle2.setId((long)i);
            spittle2.setMessage("Hello World");
            spittle2.setPostedTime(new Date());
            spittle2.setSpitter(admin);

            spittles.add(spittle2);
        }

        return spittles;
    }


    @PreFilter("hasRole('ROLE_ADMIN') or filterObject.spitter.username == authentication.principal")
    public void deleteSpittles(List<Spittle> spittles) {
        return;
    }

    @PreFilter("hasPermission(filterObject, 'delete')")
    public int updateSpittle(List<Spittle> spittle) {
        return spittle.size();
    }
}
