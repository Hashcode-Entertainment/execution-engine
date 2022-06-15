package cloud.hashcodeentertainment.executionengineservice.commons.rest;

import cloud.hashcodeentertainment.executionengineservice.manager.domain.DockerManagerException;
import cloud.hashcodeentertainment.executionengineservice.taks.domain.TaskException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cloud.hashcodeentertainment.executionengineservice.manager.domain.DockerManagerExceptionDict.ADDRESS_EXISTS;
import static cloud.hashcodeentertainment.executionengineservice.manager.domain.DockerManagerExceptionDict.ONLY_ONE_LOCAL_INSTANCE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(DockerManagerException.class)
    public ResponseEntity<ErrorResponse> handleDockerManagerException(DockerManagerException e) {
        return switch (e.getMessage()) {
            case ADDRESS_EXISTS -> ResponseEntity.status(CONFLICT).body(new ErrorResponse(e.getMessage()));
            case ONLY_ONE_LOCAL_INSTANCE -> ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
            default -> ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse("unknown reason"));
        };
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorResponse> handleTaskException(TaskException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
