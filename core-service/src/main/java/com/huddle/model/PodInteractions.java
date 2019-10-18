package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodInteractions {
    long interactionCount;
    String destinationName;
    String destinationIp;
    long latencyInMs;
}
