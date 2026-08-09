package customer;

public class CustomerFactory {

    public static Customer defaultCustomer() {
        return new Customer(
                "Viktoria",
                "Zolotareva",
                "12345"
        );
    }
}
