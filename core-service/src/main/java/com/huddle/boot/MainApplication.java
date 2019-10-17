package com.huddle.boot;

import com.huddle.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import(value = { AppConfig.class})
public class MainApplication {

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
		SpringApplication.run(MainApplication.class, args);
	}

}
