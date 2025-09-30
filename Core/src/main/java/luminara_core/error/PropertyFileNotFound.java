package luminara_core.error;

public class PropertyFileNotFound extends RuntimeException {
    public PropertyFileNotFound(String message) {
        super(message);
    }
}
