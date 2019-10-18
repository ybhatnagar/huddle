package com.huddle.model;

import lombok.Data;

import java.util.List;

@Data
public class GroupResponse {

    Long id;
    List<NodeGrouping> groups;

    @Data
    public class NodeGrouping {
        List<Node> nodes;
    }
}
