package cloud.popples.springwithorm.spittr.jpahibernete.impl;

import cloud.popples.springwithorm.spittr.domain.Spitter;
import cloud.popples.springwithorm.spittr.jpahibernete.SpitterRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addSpitter(Spitter spitter) {
        entityManager.persist(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return entityManager.find(Spitter.class, id);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        entityManager.merge(spitter);
    }
}
