package cloud.popples.springwithorm.spittr.jpaspringdata;

import cloud.popples.springwithorm.spittr.domain.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpitterRepository2 extends JpaRepository<Spitter, Long>, SpitterSweeper {

    Spitter findByUsername(String username);

    @Query("select s from Spitter s where s.email like '%gmail.com'")
    List<Spitter> findAllGmailSpitters();
}
