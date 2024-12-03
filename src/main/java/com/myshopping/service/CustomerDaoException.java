package com.myshopping.service;

@SuppressWarnings("serial")
public class CustomerDaoException extends RuntimeException {
    public CustomerDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

