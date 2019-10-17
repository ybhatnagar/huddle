package com.huddle.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PodResponse {

    private Pods[] pods;

    @Data
    public static class Pods {
        private String name;
        private String ip;
        private Float cpuRequest;
        private Float memoryRequest;
        private Float cpuLimit;
        private Float memoryLimit;
        private Nodes[] nodes;
        private Interactions[] pod;
    }

    @Data
    public static class Interactions {
        private String name;
        private String ip;

        @JsonProperty("pod|count")
        private int count;
    }

    @Data
    public static class Nodes {
        private String name;
    }
}
