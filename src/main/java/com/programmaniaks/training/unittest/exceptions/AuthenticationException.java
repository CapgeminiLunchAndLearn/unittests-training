package com.programmaniaks.training.unittest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Invalid authentication")
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1478775491562312755L;

}
