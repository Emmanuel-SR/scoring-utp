package exceptions;

public class IncorrectDaoOperation extends RuntimeException {

    public IncorrectDaoOperation(final String message) {
        super(message);
    }
    
}
