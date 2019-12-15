# Huddle
Huddle generates smart and feasible recommendations for migration plans to reduce overall latency in distributed containerized applications. More details can be found over in the wiki.
## Overview:
- Understands the kubernetes cluster state through the kubeconfig provided. 
- Understands the inherent network latencies across the nodes via the latency monitors.
- Understands the interaction patterns among your pods.
- Automatically clusters the nodes in your cluster having large latency when communicating across them, but less within each other.
- Provides this list clusters to the user to gain insights about exactly how the cluster is in terms of latency of interactions, so that user can choose to replicate services in multiple clusters if needed.
- Also if possible, provides a list of migrations in case user does not wants to migrate the application, but rather wants to reduce the latency by some intelligent migration.
## Prerequisites: 
- You need to have Purser installed and running for your kubernetes cluster(s) which you need to optimize. (https://github.com/vmware/purser)
- You need to have your kubeconfig to be able to optimize the applications, and perform the actions.
