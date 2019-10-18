package com.huddle.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Pod extends Resource {

    private static final Logger log = LoggerFactory.getLogger(Pod.class);

    private String ip;
    private Node parentNode;
    private boolean systemPod;
    private String namespace;
    private Capacity limitCapacity;
    private Capacity requestCapacity;
    private List<PodInteraction> interactions;

    public Pod(String id, String name, String ip, long limitMemoryMB, long limitCpuMillicore, long requestMemoryMB, long requestCpuMillicore) {
        super(id, name);
        this.ip = ip;
        this.limitCapacity = new Capacity(requestMemoryMB, requestCpuMillicore);
        this.requestCapacity = new Capacity(limitMemoryMB, limitCpuMillicore);
        this.interactions = Collections.emptyList();
    }

    public double getSize(){
        long l1 = requestCapacity.getCpuMillicore() / limitCapacity.getCpuMillicore();
        long l2 = requestCapacity.getMemoryMB() / limitCapacity.getMemoryMB();

        return l1/l2;
    }

    public void assignParent(Node parentNode){
        this.parentNode = parentNode;
        log.debug("Pod {} joins the Node {}", this, parentNode);
    }

    public void leftNode(){
        this.parentNode = null;
        log.debug("Pod {} left the Node {}", this, parentNode);
    }

    @Override
    public String toString() {
        return "Pod{" +
                "id=" + getId() +
                "ip=" + getIp() +
                ", limitCapacity=" + limitCapacity +
                ", requestCapacity=" + requestCapacity +
                ", parentNode=" + (parentNode == null ? null:parentNode.getName()) +
                ", name='" + this.getName() + '\'' +
                ", interactions=" + interactions +
                '}';
    }
}
