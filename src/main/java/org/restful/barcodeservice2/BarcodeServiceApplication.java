package org.restful.barcodeservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author nmishra
 * Main class for Booting application
 * At the moment, the application runs in Web container due to presence of JSP
 * TODO: Based upon deployment type property, use either Thymyleaf (JAR) or JSP (WAR)
 */
@SpringBootApplication
public class BarcodeServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeServiceApplication.class, args);
    }
}
