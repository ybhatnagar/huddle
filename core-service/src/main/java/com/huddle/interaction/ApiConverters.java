package com.huddle.interaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import com.huddle.model.K8SClusterResponse;
import com.huddle.model.dto.PodResponse;

public interface ApiConverters {
    Function<PodResponse[], K8SClusterResponse> POD_TO_CLUSTER_RESP = (podResponse) -> {
        ArrayList<K8SClusterResponse.PodInfo> clusterInfos = new ArrayList<>();
        Arrays.stream(podResponse).forEach(pod ->
                Arrays.stream(pod.getPods()).forEach(podInfo -> {
                    K8SClusterResponse.PodInfo clusterInfo = new K8SClusterResponse.PodInfo();
                    clusterInfo.setPodName(podInfo.getName());
                    clusterInfo.setNamespace("namespace1");
                    clusterInfo.setNodeName(podInfo.getNodes()[0].getName());
                    clusterInfo.setPodIp(podInfo.getIp());
                    clusterInfos.add(clusterInfo);
                }));
        K8SClusterResponse clusterResponse = new K8SClusterResponse();
        //clusterResponse.setCluster();
        //clusterInfo.setPodName();
        return clusterResponse;
    };
}