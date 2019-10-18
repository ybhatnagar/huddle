package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    Long id;
    List<NodeGrouping> groups;


    public class NodeGrouping {
        List<Node> nodes;
    }
}
