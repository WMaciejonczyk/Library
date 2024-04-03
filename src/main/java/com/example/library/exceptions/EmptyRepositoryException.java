package com.example.library.exceptions;

public class EmptyRepositoryException extends Exception {
    public EmptyRepositoryException(String message) {
        super(message);
    }
}
