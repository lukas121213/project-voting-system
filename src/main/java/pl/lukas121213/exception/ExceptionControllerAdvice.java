package pl.lukas121213.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(VotingException.class)
    @ResponseBody
    public ResponseEntity votingException(VotingException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        if (ex.getExceptionMessage().equals(ExceptionMessage.PROJECT_VOTING_CLOSED))
            httpStatus = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(httpStatus).body(ex.getExceptionMessage());
    }

}
