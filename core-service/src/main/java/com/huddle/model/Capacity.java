package com.huddle.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Capacity {
    private long cpuMillicore;
    private long memoryMB;

    public Capacity(long memoryMB, long cpuMillicore) {
        this.memoryMB = memoryMB;
        this.cpuMillicore = cpuMillicore;
    }
}
