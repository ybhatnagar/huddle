package com.huddle;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.huddle.model.GroupResponse;
import com.huddle.model.GroupResponse.NodeInternal;
import com.huddle.model.Node;
import com.huddle.model.Pod;
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
		GroupResponse groupResponse = new GroupResponse();
		groupResponse.setId(1L);

		List<NodeInternal> nodeInternals = new ArrayList<>();
		nodes.forEach(node -> {
			List<Pod> pods = new ArrayList<>(node.getPods().values());
			pods.forEach(pod -> {
				pod.setSize(pod.calculateSize());
			});
			nodeInternals.add(new NodeInternal(node.getId(), node.getName(), pods));
		});
		groupResponse.setNodes(nodeInternals);
		System.out.println(new Gson().toJson(groupResponse));
	}
}
