package com.hellofresh.demo.controller;

import com.hellofresh.demo.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Vector;

@RestController
@RequestMapping("api/")
public class Controller {

    @Autowired
    private CounterService counterService;

    // I used Vector and not List because it is thread safe.
    private Vector<Object> list = new Vector<>();

    // make the method synchronized to make the API accept concurrent requests
    @RequestMapping("/add")
    public synchronized ResponseEntity<String> addPoints(@RequestBody String pointsStr) {
        if (pointsStr.isEmpty() || pointsStr.isBlank())
            return new ResponseEntity<>("Empty Input", HttpStatus.BAD_REQUEST);

        String result = counterService.collectAllPoints(list, pointsStr);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(result);
    }

}
