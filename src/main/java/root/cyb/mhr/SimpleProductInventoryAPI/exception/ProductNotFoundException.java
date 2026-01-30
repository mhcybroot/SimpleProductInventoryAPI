package root.cyb.mhr.SimpleProductInventoryAPI.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
