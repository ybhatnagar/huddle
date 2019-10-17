package com.huddle.controller;

import com.huddle.model.ClusterResponse;
import com.huddle.model.dto.PodResponse;
import com.huddle.services.HuddleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/huddle")
public class HuddleController {

    @Autowired
    private HuddleService huddleService;

    @GetMapping("/clusters")
    public ClusterResponse getClustersInfo() {
        return huddleService.getClusterInfo();
    }

    @GetMapping("/pods")
    public PodResponse[] getPodsInfo() {
        return huddleService.getPodResponse();
    }
}
