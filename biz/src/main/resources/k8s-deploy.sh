kubectl delete deployment phm-test -n default
kubectl delete service phm-test-service -n default
kubectl apply -f k8s-config.yaml