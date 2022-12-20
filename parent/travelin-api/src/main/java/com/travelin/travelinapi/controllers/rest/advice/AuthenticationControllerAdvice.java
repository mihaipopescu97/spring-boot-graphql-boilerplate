package com.travelin.travelinapi.controllers.rest.advice;

import com.travelin.travelinapi.controllers.rest.AuthenticationController;
import com.travelin.travelinapi.dtos.ErrorResponse;
import com.travelin.travelinapi.exceptions.TokenRefreshException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 1/2/2023, Mon
 **/

@ControllerAdvice(assignableTypes = AuthenticationController.class)
public class AuthenticationControllerAdvice {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(final BadCredentialsException exception,
                                                                       final HttpServletRequest request) {
        final ErrorResponse errorResponse = createErrorResponse(exception, request, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    public ResponseEntity<ErrorResponse> handleTokenRefreshException(final TokenRefreshException exception,
                                                                     final HttpServletRequest request) {
      final ErrorResponse errorResponse = createErrorResponse(exception, request, HttpStatus.NOT_ACCEPTABLE);

      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    private ErrorResponse createErrorResponse(final Exception exception,
                                              final HttpServletRequest request,
                                              final HttpStatus httpStatus) {

        final ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(httpStatus);
        errorResponse.setUri(request.getRequestURI());
        errorResponse.setMessage(exception.getMessage());

        return errorResponse;
    }
}
