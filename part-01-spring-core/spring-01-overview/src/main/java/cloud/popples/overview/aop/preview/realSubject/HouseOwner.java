package cloud.popples.overview.aop.preview.realSubject;

import cloud.popples.overview.aop.preview.Rent;

public class HouseOwner implements Rent {

    @Override
    public void rentHouse() {
        System.out.println("HouseOwner is renting a house.");
    }
}
