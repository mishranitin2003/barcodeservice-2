# barcodeservice-2
Demonstration of knowledge with Spring Boot, Spring Web MVC and Spring Security (HTTP-Basic)
Barcode Service to return a link to client
The pattern has been used by some SaaS providers in Logistics

Deployment Steps:
- Checkout the project
- Run to create WAR
    mvn clean install
- Deploy in tomcat with at least JEE 6 profile (Servlet 3.0)

Use a link in browser:
<Server Context>/code39/12345678
Use any username and password as 'password' for now
