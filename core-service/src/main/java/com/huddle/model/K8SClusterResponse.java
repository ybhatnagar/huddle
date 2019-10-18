package com.huddle.model;

import com.huddle.model.dto.PodResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Cluster;

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
