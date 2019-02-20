# Trying to understand the strange behaviour of WebClient in tests

We are trying to execute these tests, which pass individually but fails when all are launched. 

```
$> mvn clean test
```

We isolated this case in a project where we added `WireMock` to simulate external Http calls. But WireMock has nothing to do with this issue.