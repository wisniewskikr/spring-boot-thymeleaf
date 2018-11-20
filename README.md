SPRING BOOT + THYMELEAF (HELLO WORLD) + PROFILES
================================================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Thymeleaf.
This project displays 'Hello World' message for user.
User name is stored as system property.
Detail:
Depending on profile different name is predisplayed in Input.


RUN
---

run main() method in class Application.java
or
mvn spring-boot:run -Dspring.thymeleaf.cache=false
  

USAGE
-----

Link to main UI:
http://localhost:8080


SETTING PROFILE - SPRING BOOT
-----------------------------

- in file "application.properties" property "spring.profiles.active"


SETTING PROFILE - TOMCAT
------------------------

- in file <tomcat_home>/bin/catalina.bat
  On beginning of this file (eg. line 96)
  set "JAVA_OPTS=%JAVA_OPTS% -Dspring.profiles.active=prod"
  
- in file "application.properties" property "spring.profiles.active"