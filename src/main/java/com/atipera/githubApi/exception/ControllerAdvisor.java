package com.atipera.githubApi.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GithubUserNotFound.class)
    public ResponseEntity<Object> handleGithubUserNotFoundException(
            GithubUserNotFound ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        int responseCode = HttpStatus.NOT_FOUND.value();
        String whyHasItHappened = ex.getMessage();
        body.put("status", responseCode);
        body.put("message", whyHasItHappened);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(GithubRepoNotFound.class)
    public ResponseEntity<Object> handleGithubRepoNotFoundException(
            GithubRepoNotFound ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        int responseCode = HttpStatus.NOT_FOUND.value();
        String whyHasItHappened = ex.getMessage();
        body.put("status", responseCode);
        body.put("message", whyHasItHappened);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", HttpStatus.NOT_FOUND.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
       Map<String, Object> errorResponse = new LinkedHashMap<>();
       errorResponse.put("status", HttpStatus.NOT_FOUND.value());
       errorResponse.put("title", "Not found");
       errorResponse.put("detail", ex.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .contentType(MediaType.APPLICATION_JSON)
               .body(errorResponse);
    }
}
