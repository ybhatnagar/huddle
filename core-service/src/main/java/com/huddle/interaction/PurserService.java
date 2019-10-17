package com.huddle.interaction;

import com.huddle.model.ClusterResponse;
import com.huddle.model.PodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PurserService {

    @Value("purser.base.url")
    String PURSER_BASE_URL;

    public ClusterResponse getClusterInfo(){


        //TODO: actual call via restTemplate to the purser, to get actual info
        ClusterResponse resp = new ClusterResponse();

        ClusterResponse.ClusterInternal clusterInternal  = new ClusterResponse.ClusterInternal();

        PodResponse podResponse = new PodResponse("pod1","node1","10.112.31.3","borathon");
        PodResponse podResponse2 = new PodResponse("pod2","node2","10.112.31.5","borathon");

        clusterInternal.setPods(Arrays.asList(podResponse,podResponse2));
        clusterInternal.setClusterName("Staging-eng-services");


        resp.setCluster(Arrays.asList(clusterInternal));
        return resp;
    }
}
