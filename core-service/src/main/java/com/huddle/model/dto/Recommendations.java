package com.huddle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendations {

    String pod;
    String currentNode;
    String recommendedNode;

    Long costSavings;
    Long latencyImprovement;

}
