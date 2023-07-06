package com.nttdata.msaccount.infrastructure.handler;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * Handle exceptions thrown.
 */
@ControllerAdvice
public class ApiExceptionHandler {

  /**
   * Send validation exception.
   *
   * @param ex exception.
   * @return returns a Single with the error object.
   */
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseBody
  public Single<ErrorResponse> handleValidationException(WebExchangeBindException ex) {
    Integer statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value();
    return Flowable.fromIterable(ex.getFieldErrors())
            .toMap(FieldError::getField, FieldError::getDefaultMessage, HashMap::new)
            .map(errors -> new ErrorResponse("Error de validación", statusCode, errors));
  }

  /**
   * Send bad request exception.
   *
   * @param ex exception.
   * @return returns a Single with the error object.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public Single<ErrorResponse> handleBadRequestException(Throwable ex) {
    Integer statusCode = 400;
    String message = ex.getMessage();
    return Single.just(new ErrorResponse(message, statusCode, null));
  }

}
