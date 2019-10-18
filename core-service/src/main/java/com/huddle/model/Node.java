package com.huddle.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Node extends Resource{

    private static final Logger log = LoggerFactory.getLogger(Node.class);

    private Map<String,Pod> pods;

    public Node(String id, String name) {
        super(id, name);
        pods = new ConcurrentHashMap<>();
    }

    public boolean addPod(Pod pod) {
        pods.put(pod.getId(),pod);
        pod.joinedNode(this);
        return true;
    }

    @Override
    public String toString() {
        return "Node{" +
                "pods=" + pods +
                '}';
    }
}
