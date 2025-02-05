#!/bin/bash

# Port-forward Prometheus
kubectl port-forward service/prometheus-server 9030:80 -n monitoring &
PROMETHEUS_PID=$!

# Port-forward Grafana
kubectl port-forward service/grafana 9040:80 -n monitoring &
GRAFANA_PID=$!

# Port-forward ArgoCD
kubectl port-forward svc/argocd-server -n argocd 8080:443 &
ARGOCD_PID=$!

# Port-forward Demo Java App
kubectl port-forward service/demo-java-app-service 8081:80 -n app &
DEMO_JAVA_APP_PID=$!

# Port-forward Elasticsearch
kubectl port-forward service/es-master-service 9010:9200 -n logging &
ES_MASTER_PID=$!

# Port-forward Kibana
kubectl port-forward service/kibana-service 9020:5601 -n logging &
KIBANA_PID=$!

# Function to clean up background processes
cleanup() {
  kill $PROMETHEUS_PID $GRAFANA_PID $ARGOCD_PID $DEMO_JAVA_APP_PID $ES_MASTER_PID $KIBANA_PID
}

# Trap SIGINT and SIGTERM to clean up background processes
trap cleanup SIGINT SIGTERM

# Wait for all background processes to finish
wait