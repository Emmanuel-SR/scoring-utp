package exceptions;

public class DuplicateEntryDaoOperation extends RuntimeException {

    public DuplicateEntryDaoOperation(final String message) {
        super(message);
    }
    
}
