# Distributed Tracing 

micrometer-observation dependency will allow us to collect and report metrics from our application to Zipkin.

micrometer-tracing-bridge-brave dependency will allow us to trace our spring boot application.

zipkin-reporter-brave dependency allows us to send traces that we collect to Zipkin.

feign-micrometer dependency was added because I am using feign in my microservices to call other APIs. This dependency will configure the micrometer to work with feign.Spring Cloud Sleuth library is deprecated