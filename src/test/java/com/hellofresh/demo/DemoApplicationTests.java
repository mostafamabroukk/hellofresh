package com.hellofresh.demo;

import com.hellofresh.demo.controller.Controller;
import com.hellofresh.demo.service.CounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	Controller controller;

	Vector<Object> list = new Vector<>();

	@Test
	void contextLoads() {
	}

	@Test
	public void event(){
		String str = "1607341341814,0.0442672968,1282509067\n" +
				"1607341339814,0.0473002568,1785397644\n";
		ResponseEntity<?> result = controller.addPoints(str);
		assertEquals(result.getStatusCode(), HttpStatus.ACCEPTED);
	}

	@Test
	public void addPoint(){
		String str0 = "1607341341814,0.0442672968,1282509067\n" +
				"1607341339814,0.0473002568,1785397644\n";
		controller.addPoints(str0);
		ResponseEntity<?> stats = controller.getStats();
		assertEquals(stats.getStatusCode(), HttpStatus.ACCEPTED);
	}

}
