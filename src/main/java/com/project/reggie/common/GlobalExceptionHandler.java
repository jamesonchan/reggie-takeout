package com.project.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * handling global exceptions
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * exception handling method
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        log.error(exception.getMessage());
        if (exception.getMessage().contains("Duplicate entry")) {
            String[] split = exception.getMessage().split(" ");
            String message = split[2] + "already existed";
            return R.error(message);
        }
        return R.error("operation failed please try again later");
    }
}
