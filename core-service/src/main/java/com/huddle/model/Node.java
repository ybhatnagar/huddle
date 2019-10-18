package com.huddle.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        pod.assignParent(this);
        return true;
    }

    public double getAverageLatency(Node externalNode) {
        //get average of all the latencies between pods across current node and parameter node
        Map<String, Pod> currentPodMap = this.getPods();
        Map<String, Pod> externalNodePodMap = externalNode.getPods();

        long sumLatency = 0;
        long count = 0;
        for(Entry<String, Pod> entry : currentPodMap.entrySet()) {
            List<PodInteraction> podInteractions = entry.getValue().getInteractions();
            for(PodInteraction podInteraction : podInteractions) {
                if(externalNodePodMap.containsKey(podInteraction.getDestinationName())){
                    sumLatency += podInteraction.latencyInMs;
                    count++;
                }
            }
        }
        return sumLatency/count;
    }

    @Override
    public String toString() {
        return "Node{" +
                "pods=" + pods +
                '}';
    }
}
