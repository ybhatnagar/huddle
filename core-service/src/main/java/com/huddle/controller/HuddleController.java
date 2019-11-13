package com.huddle.controller;

import java.util.List;

import com.huddle.model.K8SClusterResponse;
import com.huddle.model.GroupResponse;
import com.huddle.model.dto.PodResponse;
import com.huddle.model.dto.Recommendations;
import com.huddle.services.HuddleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/huddle")
public class HuddleController {

    @Autowired
    private HuddleService huddleService;

    @GetMapping("/pods")
    public PodResponse[] getPodsInfo() {
        return huddleService.getPodResponse();
    }


    @GetMapping("/clusters")
    public K8SClusterResponse getClustersInfo() {
        return huddleService.getClusterInfo();
    }

    @PostMapping("/groups")
    public List<GroupResponse> getNodeGrouping(K8SClusterResponse response){
        return huddleService.getGroups(response);
    }

    @PostMapping("/recommendations")
    public List<Recommendations> getRecommendations(GroupResponse response){
        return huddleService.getRecommendations(response);
    }
}
