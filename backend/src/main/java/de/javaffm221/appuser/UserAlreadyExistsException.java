package de.javaffm221.appuser;

import javax.naming.AuthenticationException;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
