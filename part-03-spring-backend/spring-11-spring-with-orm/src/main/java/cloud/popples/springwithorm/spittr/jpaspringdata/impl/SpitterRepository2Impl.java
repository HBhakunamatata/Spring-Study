package cloud.popples.springwithorm.spittr.jpaspringdata.impl;

import cloud.popples.springwithorm.spittr.jpaspringdata.SpitterSweeper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class SpitterRepository2Impl implements SpitterSweeper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int eliteSweep() {
        String update = "update Spitter s set s.updateByEmail = false " +
                "where s.email = 'test@test.com'";
        return em.createQuery(update).executeUpdate();
    }
}
