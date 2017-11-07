[![Build Status](https://travis-ci.org/kennycyb/spring-rest-api-mongodb.svg?branch=master)](https://travis-ci.org/kennycyb/spring-rest-api-mongodb)

# spring-rest-api-mongodb
Starter application for REST API with spring framework and mongodb.  This 
project is based on the https://github.com/kennycyb/spring-rest-api-starter, 
with added connectivity to MongoDB.

Refer to https://docs.spring.io/spring-boot/docs/current/reference/html/ for more information.

# pom.xml
Use WAR packaging:

	<packaging>war</packaging>

Use Spring Framwork


    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.8.RELEASE</version>
    </parent>
    
Dependencies

	<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>		
    </dependencies>
    

# Configuration: src/main/resources/application.yml

	spring:
	  data:
	    mongodb:
	      host: localhost
	      port: 27017
	      database: spring-rest-api
	      
# Externalize configuration (via Environment)

	spring:
	  data:
	    mongodb:
	      host: ${SPRING_MONGO_HOST}
	      port: ${SPRING_MONGO_PORT}
	      database: spring-rest-api
	      
## Using tomcat standalone

Edit /usr/share/tomcat8/bin/setenv.sh, add "SPRING\_MONGO\_HOST" and "SPRING\_MONGO\_PORT", example: 

	export CATALINA_OPTS=" \
	...
	...
  		-DSPRING_MONGO_HOST=localhost \
  		-DSPRING_MONGO_PORT=27017
  	"

## Deploy to OpenShift

