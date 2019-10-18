package com.huddle.services;

import com.huddle.interaction.PurserService;
import com.huddle.interaction.WavefrontService;
import com.huddle.model.ClusterResponse;
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

    public ClusterResponse getClusterInfo() {
        return purserService.getClusterInfo();
    }

    public GroupResponse getGroups(ClusterResponse response){

        //TODO: do it.
        return null;
    }

    public PodResponse[] getPodResponse() {
        return purserService.getPodResponse();
    }
}
