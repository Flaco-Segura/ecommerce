# ecommerce

ecommerce is just a **Spring Boot/AngularJS** learning project.

**Requeriments (I used):**
- openjdk: 11.0.8
- Angular CLI: 9.0.1
- Node: 13.5.0
- npm: 6.14.8 
- mysql: 14.14

### Backend
In the folder starter-files you will find the scripts to populate the DB. The name of the DB is `full-stack-ecommerce`.
I recommend to use the same one or to modify the scripts so that they agree with your DB.
I recommend to use a default characterset `UTF-8` because there are fonts not supported by other types in the DB.
I case of add or update some entity I recomemend to use the magic command `mvn clean install`.
With DB populated you just run the project and test it in: `https://localhost:8080/api/products`, by i.e.

### Frontend
Just remember to make `npm install` for the dependencies.
For run the frontend use `ng serve`.
