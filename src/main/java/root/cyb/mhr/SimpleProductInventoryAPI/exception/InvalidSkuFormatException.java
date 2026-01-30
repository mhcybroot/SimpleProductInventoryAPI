package root.cyb.mhr.SimpleProductInventoryAPI.exception;

public class InvalidSkuFormatException extends RuntimeException {
    public InvalidSkuFormatException(String message) {
        super(message);
    }
}
