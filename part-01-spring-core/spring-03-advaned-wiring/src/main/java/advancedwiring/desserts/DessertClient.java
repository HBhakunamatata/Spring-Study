package advancedwiring.desserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DessertClient {

    private Dessert iceCream;

    private Dessert cookies;

    @Autowired
    @Cold
    @Creamy
    public void setIceCream(Dessert iceCream) {
        this.iceCream = iceCream;
    }

    @Autowired
    @Crispy
    public void setCookies(Dessert cookies) {
        this.cookies = cookies;
    }

    public Dessert getIceCream() {
        return iceCream;
    }

    public Dessert getCookies() {
        return cookies;
    }
}
