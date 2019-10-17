package com.huddle.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Getter
@Setter
public class Pod extends Resource {

    private static final Logger log = LoggerFactory.getLogger(Pod.class);

    private Capacity request;

    private Node parentNode;

    boolean systemPod;

    public Pod(String id, String name, long memoryMB, long cpuMillicore,boolean isSystem) {
        super(id, name);
        this.request = new Capacity(memoryMB, cpuMillicore);
        systemPod=isSystem;
    }


    private List<PodInteractions> interactions;

    String ip;
}
