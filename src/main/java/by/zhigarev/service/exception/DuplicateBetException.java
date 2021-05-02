package by.zhigarev.service.exception;

public class DuplicateBetException extends ServiceException {
    public DuplicateBetException() {
    }

    public DuplicateBetException(String message) {
        super(message);
    }

    public DuplicateBetException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBetException(Throwable cause) {
        super(cause);
    }
}
