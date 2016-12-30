package com.goeuro.busRoute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by ganeshnagarajan on 12/28/16.
 */
@SpringBootApplication
public class BusRouteApplication {
    private static final Logger log = LoggerFactory.getLogger(BusRouteApplication.class);

    public static void main(String args[]){
        String fileLoc = "";
        if (args.length == 0) {
            fileLoc = "src/main/resources/BusRoutes.txt";
            log.info("No Bus route file location passed in the argument. Default file loaded from " + fileLoc);
        } else {
            fileLoc = args[0];
            log.info("Bus Route file location loaded from " + fileLoc);
        }
        new SpringApplicationBuilder(BusRouteApplication.class).properties("fileLoc=".concat(fileLoc)).run(args);
        log.info("BusRouteApplication started successfully");
    }
}
