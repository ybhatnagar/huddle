package com.huddle.services;

import java.util.Collections;
import java.util.List;

import com.huddle.interaction.PurserService;
import com.huddle.interaction.WavefrontService;
import com.huddle.model.K8SClusterResponse;
import com.huddle.model.GroupResponse;
import com.huddle.model.dto.PodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuddleService {

    @Autowired
    private PurserService purserService;

    @Autowired
    private WavefrontService wavefrontService;

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
}
