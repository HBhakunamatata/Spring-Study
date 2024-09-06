package cloud.popples.workwithremote.spittr.exporter;

import cloud.popples.workwithremote.spittr.domain.Spitter;
import cloud.popples.workwithremote.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "SpitterService")
public class SpitterServiceExporter extends SpringBeanAutowiringSupport {

    @Autowired
    private SpitterService spitterService;

    @WebMethod
    public Spitter getSpitterById(long id) {
        System.out.println("get param " + id);
        Spitter spitter =
                new Spitter(1L, "username", "password", "fullname", "email", true);
        return spitter;
    }

    @WebMethod
    public void saveSpitters(List<Spitter> spitterList) {
        System.out.println(spitterList);
    }
}
