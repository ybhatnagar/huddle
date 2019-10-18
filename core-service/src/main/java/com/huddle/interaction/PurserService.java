package com.huddle.interaction;

import java.util.Arrays;

import com.huddle.http.RestClient;
import com.huddle.model.K8SClusterResponse;
import com.huddle.model.K8SClusterResponse.K8SCluster;
import com.huddle.model.dto.PodResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.huddle.interaction.ApiConverters.POD_TO_CLUSTER_RESP;

@Service
@Slf4j
public class PurserService {

    private static final String PODS_METRICS_API_PATH = "/api/internal/pods/metrics";

    @Autowired
    private RestClient restClient;

    @Value("${purser.base.url:http://10.112.140.15}")
    private String purserBaseUrl;

    public PodResponse[] getPodResponse() {
        ResponseEntity<PodResponse[]> responseEntity;
        responseEntity = restClient.request(purserBaseUrl, PODS_METRICS_API_PATH, null,
                PodResponse[].class, HttpMethod.GET, null);
        log.info("response received: {}", responseEntity.toString());
        return responseEntity.getBody();
    }

    public K8SClusterResponse getClusterInfo() {
        //TODO: actual call via restTemplate to the purser, to get actual info
        K8SClusterResponse resp = new K8SClusterResponse();

        K8SCluster k8SCluster = new K8SCluster();

        //PodResponse podResponse = new PodResponse("pod1", "node1", "10.112.31.3", "borathon");
        //PodResponse podResponse2 = new PodResponse("pod2", "node2", "10.112.31.5", "borathon");
        PodResponse[] podResponse = getPodResponse();
        POD_TO_CLUSTER_RESP.apply(podResponse);

        //clusterInternal.setPods(Arrays.asList(podResponse, podResponse2));
        k8SCluster.setK8sClusterName("Staging-eng-services");

        resp.setK8SCluster(Arrays.asList(k8SCluster));
        return resp;
    }
}