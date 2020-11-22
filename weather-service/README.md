## This is backend service for weather app feature

Technology used

1. Spring Boot
2. H2
3. Rest Service
4. Open Weather API

Before generating docker image, put your open weather api key in application.yml for key api.openweathermap.key

Run below command to generate docker image for backend service from root folder.

Below command fetches all the libraries so it will take 5-10 mins for first time .

```
cd weather-service
./gradlew bootBuildImage
```

Above command builts image 'docker.io/library/weather-service:0.0.1-SNAPSHOT'

Verify whether image has successfully built by below command:

```
docker images | grep weather-service
```

Now to run this service execute below command:

```
docker run -d -p 8080:8080 --name weather-service weather-service:0.0.1-SNAPSHOT
```

To verify service is working properly execute below url in browser

```
http://localhost:8080/weather/1
```
