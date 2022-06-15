package com.hellofresh.demo;

import com.hellofresh.demo.service.CounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CounterService counterService;

	Vector<Object> list = new Vector<>();

	@Test
	void contextLoads() {
	}

	@Test
	public void initialisePoints(){
		String str = "1607341341814,0.0442672968,1282509067\n" +
				"1607341339814,0.0473002568,1785397644\n";
		String result = counterService.collectAllPoints(list, str);
		assertEquals(result, "2,0.0915675536,0.0457837768,3067906711,1533953355.000");
	}

	@Test
	public void addPoint(){
		String str0 = "1607341341814,0.0442672968,1282509067\n" +
				"1607341339814,0.0473002568,1785397644\n";
		counterService.collectAllPoints(list, str0);

		String str = "1607341341984,0.04132968,128\n";
		String result = counterService.collectAllPoints(list, str);
		assertEquals(result, "3,0.1328972317,0.0442990772,3067906839,1022635613.000");
	}

}
