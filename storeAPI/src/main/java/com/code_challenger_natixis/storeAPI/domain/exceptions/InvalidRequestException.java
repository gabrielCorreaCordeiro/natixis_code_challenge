package com.code_challenger_natixis.storeAPI.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidRequestException extends Exception {
    public InvalidRequestException(String ex) {
        super(ex);
    }
}