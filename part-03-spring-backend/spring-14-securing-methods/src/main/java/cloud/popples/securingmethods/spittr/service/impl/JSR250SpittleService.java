package cloud.popples.securingmethods.spittr.service.impl;

import cloud.popples.securingmethods.spittr.domain.Spittle;
import cloud.popples.securingmethods.spittr.service.SpittleService;

import javax.annotation.security.RolesAllowed;

public class JSR250SpittleService implements SpittleService {
    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SPITTER"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Calling addSpittle successfully");
    }
}
