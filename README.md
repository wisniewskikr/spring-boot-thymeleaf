SPRING BOOT + THYMELEAF + TOMCAT BASIC AUTHENTICATION
=====================================================


DESCRIPTION
-----------

This is example project of usage Spring Boot + Thymeleaf.
This project displays 'Hello World' message for user.
This is example of usage Tomcat basic authentication.
Attention!!!
Authentication works only on Tomcat server.


PRECONDITIONS
-------------

On Tomcat in location <tomcat_home>/conf/tomcat-users.xml
please add following user:
<user username="demo" password="password" roles="demo"/>
  

USAGE
-----

To run project run class: 
Application.java

Link to main UI:
http://localhost:8080/app

Credentials: demo/password