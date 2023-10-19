# Exchange Rate API


## Getting started

This is a simple API to get exchange rates and to convert an amount of money to another currencies.

The documentation for this service API can be [accessed here](https://app.swaggerhub.com/apis/PRTPEREIRA2/ExchangeRatesAPI/1.0.0) or locally at [./BVG-ExchangeRatesAPI/docs/swagger-api-doc-html2/index.html](./docs/swagger-api-doc-html2/index.html).

## Start the application

#### Running the app

```
 docker-compose build
 docker-compose up app
```

The above commands, will build and run a docker container, starting this web service on localhost at port 8081.

#### Supported endpoints

As mentioned earlier you can check all the supported endpoints [here](https://app.swaggerhub.com/apis/PRTPEREIRA2/ExchangeRatesAPI/1.0.0) or locally at [./BVG-ExchangeRatesAPI/docs/swagger-api-doc-html2/index.html](./docs/swagger-api-doc-html2/index.html).

#### Calling the API

After the docker container is running, you can use an API requester of you choose, like Postman, or even using the `curl` command, to start to perform some requests into the web service.

Examples:
```
GET http://localhost:8081/api/rate?from=EUR&to=GBP

GET http://localhost:8081/api/rates?from=EUR

GET http://localhost:8081/api/convert?from=EUR&to=GBP,LAK,CAD,USD&amount=100.23
```