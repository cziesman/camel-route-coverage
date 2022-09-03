package com.redhat.consulting.camel.route.coverage.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.MultiValueMap;

@Data
@Builder
public class RouteStatistic {

    private String id;

    private int totalEips;

    private int totalEipsTested;

    private int totalProcessingTime;

    private int coverage;

    private boolean totalEipsInitialized;

    private MultiValueMap<Integer, EipStatistic> eipStatisticMap;
}
