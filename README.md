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