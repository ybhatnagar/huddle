package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClusterResponse {
    List<ClusterInternal> cluster;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClusterInternal {
        String clusterName;
        List<PodResponse> pods;
    }
}
