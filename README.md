### Simple eCommerce

This repo has code for two applications:

1. SpringBoot Java eCommerce backend that uses an in-memory database, H2. The data will be wiped after restart of the app.
2. ReactJS storefront that is served by the Spring Boot app

#### Live site
You can access the live site at: https://ecommerce.edgecloud9.com/

Things to note:
* To populate the database with seed data, hit the following URL: https://ecommerce.edgecloud9.com/api/products/seeds
* The hostname, ecommerce.edgecloud9.com, is akamized, meaning behind Akamai CDN
* Product images are actually rendered by the SpringBoot app in real-time, hence the slowness

#### Run locally
To run the SpringBoot app, you would first need to install Java SDK 17+. For ReactJS app, you would need to install Node and NPM.

The following command will compile and run both apps.
```dtd
$ ./gradlew build bootRun
```
You can access the site using: http://localhost:8080
To seed the database, hit the url: http://localhost:8080/api/products/seeds
