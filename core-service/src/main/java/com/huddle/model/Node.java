package com.huddle.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Getter
@Setter
public class Node extends Resource{

    private static final Logger log = LoggerFactory.getLogger(Node.class);

    private static final long CPU_HEADROOM = 0;
    private static final long MEM_HEADROOM = 0;

    private Map<String,Pod> pods;

    private Capacity totalCapacity;

    private Capacity availableCapacity;

    public Node(String id, String name, long memoryMB, long cpuMillicore) {
        super(id, name);
        this.totalCapacity = new Capacity(memoryMB, cpuMillicore);
        this.availableCapacity = new Capacity(memoryMB, cpuMillicore);
        this.pods = new ConcurrentHashMap<>();
    }


}
