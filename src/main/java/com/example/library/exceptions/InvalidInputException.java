package com.example.library.exceptions;

/**
 * Represents an exception that is thrown when an invalid input is encountered.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

