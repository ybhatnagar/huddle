package com.huddle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huddle.contracts.ClusterResponse;
import com.huddle.contracts.PodResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		ObjectMapper  mapper = new ObjectMapper();
//		ClusterResponse resp = new ClusterResponse();
//		PodResponse podResponse = new PodResponse("pod1","node1","10.112.31.3","borathon");
//		PodResponse podResponse2 = new PodResponse("pod2","node2","10.112.31.5","borathon");
//
//		resp.setPods(Arrays.asList(podResponse,podResponse2));
//		resp.setClusterName("Staging-eng-services");
//		try{
//			System.out.println(mapper.writeValueAsString(resp));
//		} catch ( Exception e){
//
//		}
		SpringApplication.run(Application.class, args);
	}

}
