package root.cyb.mhr.SimpleProductInventoryAPI.exception;

public class SkuAlreadyExistsException extends RuntimeException {
    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}
