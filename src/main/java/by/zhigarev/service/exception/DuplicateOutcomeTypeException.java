package by.zhigarev.service.exception;

public class DuplicateOutcomeTypeException extends ServiceException {
    public DuplicateOutcomeTypeException() {
    }

    public DuplicateOutcomeTypeException(String message) {
        super(message);
    }

    public DuplicateOutcomeTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateOutcomeTypeException(Throwable cause) {
        super(cause);
    }
}
