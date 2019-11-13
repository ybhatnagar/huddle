package com.huddle.services;

import java.util.Collections;
import java.util.List;

import com.huddle.interaction.PurserService;
import com.huddle.interaction.WavefrontService;
import com.huddle.model.K8SClusterResponse;
import com.huddle.model.GroupResponse;
import com.huddle.model.dto.PodResponse;
import com.huddle.model.dto.Recommendations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuddleService {

    @Autowired
    private PurserService purserService;

    @Autowired
    private WavefrontService wavefrontService;

    @Autowired
    private RecommendationService recommendationService;

    public K8SClusterResponse getClusterInfo() {
        return purserService.getClusterInfo();
    }

    public List<GroupResponse> getGroups(K8SClusterResponse response){
        //Iterate each node, generate list of GroupResponse, calculate score and get the list with max score
        
        return Collections.emptyList();
    }

    public PodResponse[] getPodResponse() {
        return purserService.getPodResponse();
    }

    public List<Recommendations> getRecommendations(GroupResponse toBeOptimized){
        return recommendationService.getRecommendations(toBeOptimized);
    }
}
