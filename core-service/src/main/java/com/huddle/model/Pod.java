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
    private String parentNodeId;
    private boolean systemPod;
    private String namespace;
    private Capacity limitCapacity;
    private Capacity requestCapacity;
    private List<PodInteraction> interactions;
    private Double size;

    public Pod(String id, String name, String ip, long limitMemoryMB, long limitCpuMillicore, long requestMemoryMB, long requestCpuMillicore) {
        super(id, name);
        this.ip = ip;
        this.requestCapacity = new Capacity(requestMemoryMB, requestCpuMillicore);
        this.limitCapacity = new Capacity(limitMemoryMB, limitCpuMillicore);
        this.interactions = Collections.emptyList();
    }

    public double calculateSize(){
        double l1 = (double) requestCapacity.getCpuMillicore() /  (double)limitCapacity.getCpuMillicore();
        double l2 =  (double)requestCapacity.getMemoryMB() /  (double)limitCapacity.getMemoryMB();

        return l1/l2;
    }

    public void assignParent(String parentId){
        this.parentNodeId = parentId;
        log.debug("Pod {} joins the Node {}", this, parentNodeId);
    }

    @Override
    public String toString() {
        return "Pod{" +
                "id=" + getId() +
                "ip=" + getIp() +
                ", limitCapacity=" + limitCapacity +
                ", requestCapacity=" + requestCapacity +
                ", parentNodeId=" + parentNodeId +
                ", name='" + this.getName() + '\'' +
                ", interactions=" + interactions +
                '}';
    }
}
