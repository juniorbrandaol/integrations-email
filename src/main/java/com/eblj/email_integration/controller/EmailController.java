package com.eblj.email_integration.controller;

import com.eblj.email_integration.dto.EmailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void send( @RequestBody EmailDTO dto){

    }
}
