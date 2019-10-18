package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

    Long id;
    List<NodeGrouping> groups;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NodeGrouping {
        List<Node> nodes;
    }
}
