package com.huddle.interaction;

import java.util.function.Function;

import com.huddle.model.ClusterResponse;
import com.huddle.model.dto.PodResponse;

public interface ApiConverters {

        Function<PodResponse[], ClusterResponse> POD_TO_CLUSTER_RESP = (podResponse) -> {
            ClusterResponse clusterResponse = new ClusterResponse();
            //clusterResponse.setCluster();
            return clusterResponse;
        };
}
