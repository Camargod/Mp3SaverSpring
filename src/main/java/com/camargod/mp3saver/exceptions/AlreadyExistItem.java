package com.camargod.mp3saver.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class AlreadyExistItem extends RuntimeException{
    public AlreadyExistItem(String message){
        super(message);
    }
}
