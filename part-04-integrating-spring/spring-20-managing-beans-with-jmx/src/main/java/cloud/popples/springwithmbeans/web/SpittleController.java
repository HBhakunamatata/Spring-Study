package cloud.popples.springwithmbeans.web;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;

@Controller
@ManagedResource(objectName = "spitter:name=SpittleController")
public class SpittleController {

    public static final int DEFAULT_SPITTLES_PER_PAGE = 25;

    private int spittlesPerPage = DEFAULT_SPITTLES_PER_PAGE;

    @ManagedAttribute
    public int getSpittlesPerPage() {
        return spittlesPerPage;
    }

    @ManagedAttribute
    public void setSpittlesPerPage(int spittlesPerPage) {
        this.spittlesPerPage = spittlesPerPage;
    }

    @ManagedOperation
    public int countSpittles() {
        return 0;
    }

    public int sendSpittles() {
        return 0;
    }
}
