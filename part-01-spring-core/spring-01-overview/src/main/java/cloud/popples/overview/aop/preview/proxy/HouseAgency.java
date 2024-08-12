package cloud.popples.overview.aop.preview.proxy;


import cloud.popples.overview.aop.preview.Rent;

// static proxy
public class HouseAgency implements Rent {

    private Rent rent;

    public HouseAgency() { }

    public HouseAgency (Rent rent) {
        this.rent = rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @Override
    public void rentHouse() {
        System.out.println("Now agency takes over...");
        showHouse();
        rent.rentHouse();
        signContract();
        System.out.println("Now agency ends...");
    }

    public void showHouse() {
        System.out.println("Show house to client");
    }

    public void signContract () {
        System.out.println("Sign a contract");
    }
}