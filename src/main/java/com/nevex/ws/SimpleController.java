package com.nevex.ws;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mcunningham on 8/3/2017.
 */
@RestController
public class SimpleController {

    @GetMapping(value = "/" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return "{ \"message\": \"Hello\" } ";
    }

}
