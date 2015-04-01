package com.programmaniaks.training.unittest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Invalid authentication")
public class AuthenticationException extends RuntimeException {

}
