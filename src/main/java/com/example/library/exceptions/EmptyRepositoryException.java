package com.example.library.exceptions;

/**
 * Represents an exception that is thrown when a repository is empty.
 */
public class EmptyRepositoryException extends Exception {

    /**
     * Constructs a new EmptyRepositoryException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public EmptyRepositoryException(String message) {
        super(message);
    }
}

