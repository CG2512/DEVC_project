Project for Developing for the Cloud(DEVC) module. The apps is split into 3 microservices(Book service,User service,Rating service) for the back end
and the front end use Thymeleaf view engine for dynamic template.The project uses MySQL/MariaDB for database. The back/front end communicate by using API call(with Spring Cloud Feign library)
The project is deployed on AWS EC2 (seperate instances for each microservices
Included in the file are development/deployment versions. The deployment version switch CloudFeign's API address from 'localhost' to the EC2's private IP
