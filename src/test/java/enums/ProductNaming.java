package enums;

public enum ProductNaming {
    BACKPACK("Sauce Labs Backpack", "$29.99"),
    BIKE("Sauce Labs Bike Light", "$9.99"),
    TSHIRT("Sauce Labs Bolt T-Shirt", "$15.99");

    private final String displayName;
    private final String price;

    ProductNaming(String displayName, String price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrice() {
        return price;
    }
}
