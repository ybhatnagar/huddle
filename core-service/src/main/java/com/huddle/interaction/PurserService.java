package com.huddle.interaction;

import java.util.Arrays;

import com.huddle.http.RestClient;
import com.huddle.model.ClusterResponse;
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

    public ClusterResponse getClusterInfo() {
        //TODO: actual call via restTemplate to the purser, to get actual info
        ClusterResponse resp = new ClusterResponse();

        ClusterResponse.ClusterInternal clusterInternal = new ClusterResponse.ClusterInternal();

        //PodResponse podResponse = new PodResponse("pod1", "node1", "10.112.31.3", "borathon");
        //PodResponse podResponse2 = new PodResponse("pod2", "node2", "10.112.31.5", "borathon");
        PodResponse[] podResponse = getPodResponse();
        POD_TO_CLUSTER_RESP.apply(podResponse);

        //clusterInternal.setPods(Arrays.asList(podResponse, podResponse2));
        clusterInternal.setClusterName("Staging-eng-services");

        resp.setCluster(Arrays.asList(clusterInternal));
        return resp;
    }
}