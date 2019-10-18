package com.huddle.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.huddle.model.Node;
import com.huddle.model.Pod;
import com.huddle.model.PodInteractions;

public class DataGenerator {
    public static List<Node> generate(int nodes, int podOnEachNode) {
        List<Node> kubeEnv = new ArrayList<>();
        for(int node=0; node < nodes; node++) {
            Node node1 = new Node(node+"", node+"");
            for (int pod=0; pod < podOnEachNode; pod++) {
                Pod newPod = generatePod(node+"_"+pod, node+"_"+pod);
                boolean added = node1.addPod(newPod);
                if (!added)
                    break;
            }
            kubeEnv.add(node1);
        }
        generatePodInteractions(kubeEnv);
        return kubeEnv;
    }

    private static void generatePodInteractions(List<Node> nodes) {
        //Pick one pod and set random interactions with other pod
        
    }

    private static Pod generatePod(String podId, String name) {
        int requestMemoryMB = generateRandomIntBetween(1, 20) * 100;
        int requestCpuMillicore = generateRandomIntBetween(1, 20) * 50;

        int limitMemoryMB = 1024;
        int limitCpuMillicore = 1000;

        String ip = "10.112." + generateRandomIntBetween(1, 100) + "." + generateRandomIntBetween(1, 100);
        return new Pod(podId,name, ip, limitMemoryMB, limitCpuMillicore,requestMemoryMB, requestCpuMillicore);
    }

    private static int generateRandomIntBetween(int start, int end) {
        Random r = new Random();
        return r.nextInt(end-start) + start;
    }
}
