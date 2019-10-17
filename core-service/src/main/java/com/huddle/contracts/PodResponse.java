package com.huddle.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PodResponse {

    String podName;
    String nodeName;

    String podIp;
    String namespace;


}
