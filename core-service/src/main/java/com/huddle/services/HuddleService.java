package com.huddle.services;

import com.huddle.interaction.PurserService;
import com.huddle.interaction.WavefrontService;
import com.huddle.model.ClusterResponse;
import com.huddle.model.GroupResponse;
import com.huddle.model.PodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HuddleService {

    @Autowired
    PurserService purserService;

    @Autowired
    WavefrontService wavefrontService;


    public ClusterResponse getClusterInfo(){
        return purserService.getClusterInfo();
    }

    public GroupResponse getGroups(){

        //TODO: do it.
        return null;
    }
}
