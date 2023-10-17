package ru.neoflex.products.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.products.dtos.ErrorResponse;
import ru.neoflex.products.exceptions.NotFoundException;
import ru.neoflex.products.exceptions.ProductsException;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException e) {
        ErrorResponse response = new ErrorResponse(e.getValue() + " - invalid value");
        log.warn("InvalidFormatException {}", response);
        return response;
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handleConstraintValidationException(ConstraintViolationException e) {
        List<ErrorResponse> response = e.getConstraintViolations().stream()
                .map(error -> new ErrorResponse(error.getMessage()))
                .toList();
        log.warn("ConstraintViolationException {}", response);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ErrorResponse> response = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getDefaultMessage()))
                .toList();
        log.warn("MethodArgumentNotValidException {}", response);
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("NotFoundException {}", response);
        return response;
    }

    @ExceptionHandler(ProductsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleProductsException(ProductsException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("ProductsException {}", response);
        return response;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ErrorResponse response = new ErrorResponse(String.format("Required request parameter %s is not present", e.getParameterName()));
        log.warn("MissingServletRequestParameterException {}", response);
        return response;
    }
}
