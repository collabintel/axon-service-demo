# Run In OpenShift
To Create An Application From This Repository:

```
oc new-app https://github.com/masterhead/axon-service-demo.git --context-dir=product-query --strategy=docker --name=product-query
```