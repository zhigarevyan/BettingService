package by.zhigarev.service.exception;

public class DuplicateParticipantException extends ServiceException{
    public DuplicateParticipantException() {
    }

    public DuplicateParticipantException(String message) {
        super(message);
    }

    public DuplicateParticipantException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateParticipantException(Throwable cause) {
        super(cause);
    }
}
