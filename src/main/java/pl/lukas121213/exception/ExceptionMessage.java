package pl.lukas121213.exception;

public enum ExceptionMessage {

    PROJECT_NOT_FOUND("Project not found"),
    VOTER_NOT_FOUND("Voter not found"),
    PROJECT_VOTING_CLOSED("Project closed for voting");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
