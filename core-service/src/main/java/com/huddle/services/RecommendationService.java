package com.huddle.services;

import com.huddle.model.GroupResponse;
import com.huddle.model.dto.Recommendations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecommendationService {

    public List<Recommendations> getRecommendations(GroupResponse response){
        return Arrays.asList(new Recommendations("lint-app","palash-node-1","palash-node-2",12L,213L));
    }



}

