package cloud.popples.securingmethods.spittr.service.impl;

import cloud.popples.securingmethods.spittr.domain.Spittle;
import cloud.popples.securingmethods.spittr.service.SpittleService;
import org.springframework.security.access.annotation.Secured;

public class SecuredSpittleService implements SpittleService {
    @Override
    @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Call method successfully");
    }
}
