package com.huddle;

import java.util.List;

import com.huddle.model.Node;
import com.huddle.simulator.DataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ApplicationTests {

	@Test
	public void dummyTest() {
		List<Node> nodes = DataGenerator.generate(5, 2);
		System.out.println(nodes);
	}
}
