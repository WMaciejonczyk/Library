package com.example.library.exceptions;

/**
 * Represents an exception that is thrown when a user already exists.
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
