package cloud.popples.overview.dioc.newway;


import cloud.popples.overview.aop.Minstrel;
import cloud.popples.overview.dioc.Knight;
import cloud.popples.overview.dioc.Quest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }

    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    @Bean
    public Minstrel minstrel() {
        return new Minstrel(System.out);
    }
}
