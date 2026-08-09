package enums;

public enum ProductNaming {
    BACKPACK("Sauce Labs Backpack"),
    BIKE("Sauce Labs Bike Light"),
    TSHIRT("Sauce Labs Bolt T-Shirt");

    ProductNaming(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }
}
