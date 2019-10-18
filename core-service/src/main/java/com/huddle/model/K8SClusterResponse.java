package com.huddle.model;

import lombok.Data;

import java.util.List;

@Data
public class K8SClusterResponse {
    List<K8SCluster> k8SCluster;

    @Data
    public static class K8SCluster {
        String k8sClusterName;
        List<PodInfo> podInfo;
    }

    @Data
    public static class PodInfo {
        private String podName;
        private String nodeName;
        private String podIp;
        private String namespace;
    }

}
