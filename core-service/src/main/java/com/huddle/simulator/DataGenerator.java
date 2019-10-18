package com.huddle.simulator;

import java.util.ArrayList;
import java.util.Arrays;
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
        Node node1 = nodes.get(0);
        Node node2 = nodes.get(0);
        Node node3 = nodes.get(0);
        Node node4 = nodes.get(0);
        Node node5 = nodes.get(0);

        Pod pod1_1 = node1.getPods().get(0);
        Pod pod1_2 = node1.getPods().get(1);

        Pod pod2_1 = node1.getPods().get(0);
        Pod pod2_2 = node1.getPods().get(1);

        Pod pod3_1 = node1.getPods().get(0);
        Pod pod3_2 = node1.getPods().get(1);

        Pod pod4_1 = node1.getPods().get(0);
        Pod pod4_2 = node1.getPods().get(1);

        Pod pod5_1 = node1.getPods().get(0);
        Pod pod5_2 = node1.getPods().get(1);

        PodInteractions podInteractions1_1_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions1_1_1));

        PodInteractions podInteractions1_2_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions1_2_1));

        PodInteractions podInteractions2_1_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions2_1_1));

        PodInteractions podInteractions2_2_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions2_2_1));

        PodInteractions podInteractions3_1_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions3_1_1));

        PodInteractions podInteractions3_2_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions3_2_1));

        PodInteractions podInteractions4_1_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions4_1_1));

        PodInteractions podInteractions4_2_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions4_2_1));

        PodInteractions podInteractions5_1_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions5_1_1));

        PodInteractions podInteractions5_2_1 = new PodInteractions("", "", 0, 0);
        pod1_1.setInteractions(Arrays.asList(podInteractions5_2_1));
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
