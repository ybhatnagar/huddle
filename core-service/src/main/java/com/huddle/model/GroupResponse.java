package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

    Long id;
    List<NodeInternal> nodes = Collections.emptyList();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NodeInternal {
        String id;
        String name;
        List<Pod> pods;
    }
}
