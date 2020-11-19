# Package spring boot app and run on kubernetes

## Installation



```bash
cd to two dir, build each service and push to docker.

./mvnw clean package
./mvnw install dockerfile:build
docker push <DOCKER-USERNAME-REPO>/rest-service:latest
kubectl run my-springboot-k8s-sample --image=<DOCKER-USERNAME-REPO>/rest-service:latest --port=8080
kubectl get deployment
kubectl expose deployment my-springboot-k8s-sample --type="NodePort" --port 8080
kubectl get svc
```
## License

Apache-2.0 © [maying]()
