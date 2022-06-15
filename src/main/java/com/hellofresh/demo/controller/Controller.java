package com.hellofresh.demo.controller;

import com.hellofresh.demo.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("api/")
public class Controller {

    @Autowired
    private CounterService counterService;

    // I used Vector and not List because it is thread safe.
    public Vector<Object> list = new Vector<>();

    // make the method synchronized to make the API accept concurrent requests
    @PostMapping("/event")
    public synchronized ResponseEntity<?> addPoints(@RequestBody String pointsStr) {
        if (pointsStr.isEmpty() || pointsStr.isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty Input");

        counterService.collectAllPoints(this.list, pointsStr);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).body("Data was successfully processed");
    }

    @GetMapping("/stats")
    public ResponseEntity<String> getStats() {
        String result = counterService.getStatsString(this.list);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(result);
    }

}
