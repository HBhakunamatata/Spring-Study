package cloud.popples.overview.aop;

import cloud.popples.overview.aop.preview.Client;
import cloud.popples.overview.aop.preview.Rent;
import cloud.popples.overview.aop.preview.proxy.HouseAgency;
import cloud.popples.overview.aop.preview.proxy.RentHandler;
import cloud.popples.overview.aop.preview.realSubject.HouseOwner;
import org.junit.Test;

public class ProxyTest {

    @Test
    public void staticProxy () {

        Client client = new Client();

        Rent houseOwner = new HouseOwner();

        Rent agency = new HouseAgency(houseOwner);

        agency.rentHouse();
    }

    @Test
    public void dynamicProxy () {

        Rent houseOwner = new HouseOwner();

        RentHandler rentHandler = new RentHandler();

        rentHandler.setProxied(houseOwner);

        Rent proxy = (Rent) rentHandler.getProxy();

        proxy.rentHouse();

    }
}
