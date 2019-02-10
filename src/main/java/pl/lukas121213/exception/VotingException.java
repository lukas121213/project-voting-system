package pl.lukas121213.exception;

public class VotingException extends RuntimeException {

    private ExceptionMessage message;

    public VotingException(ExceptionMessage message) {
        this.message = message;
    }

    ExceptionMessage getExceptionMessage() {
        return message;
    }
}
