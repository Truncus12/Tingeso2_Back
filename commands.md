minikube start

kubectl apply -f postgres-secrets.yaml
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-dp-sv-pvc.yaml

kubectl exec -it <> -- psql -h postgres -U postgres

CREATE DATABASE microstudent;
CREATE DATABASE microfee;
CREATE DATABASE microlast;

kubectl apply -f front-end-tingeso-deployment-service.yaml (2m30s)
kubectl apply -f config-deployment-service.yaml
kubectl apply -f eureka-deployment-service.yaml
kubectl apply -f gateway-deployment-service.yaml

kubectl apply -f student-deployment-service.yaml
kubectl apply -f fee-deployment-service.yaml
kubectl apply -f last-deployment-service.yaml

---
kubectl logs  -f

---

minikube service front-end-tingeso-service
minikube tunnel

---
Ejemplos:

Ingresar datos:
209998887
Javier
Perez
2000-01-01
privado
los montes
2018

Escoger pago:
209998887
cuotas
3
2023-11-17
