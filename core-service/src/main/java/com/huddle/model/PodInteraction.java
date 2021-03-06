package com.huddle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodInteraction {
    String destinationName;
    String destinationIp;
    long interactionCount;
    long latencyInMs;
}
