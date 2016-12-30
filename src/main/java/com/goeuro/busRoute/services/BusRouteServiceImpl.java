package com.goeuro.busRoute.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by ganeshnagarajan on 12/28/16.
 */
@Scope(value = "singleton")
@Service
public class BusRouteServiceImpl implements BusRouteService {

    private static final Logger log = LoggerFactory.getLogger(BusRouteService.class);

    @Value("${fileLoc}")
    private String fileLoc;

    private Map<Integer,Set<Integer>> busRoutes;

    @PostConstruct
    public void fetchBusRoutes() {
        log.info("Bean is about to load the bus routes from " + fileLoc);
        Map<Integer,Set<Integer>> routeMap = new HashMap<>();

        //Java 8 stream makes application by utilizing multiprocessing
        try(Stream<String> stream = Files.lines(Paths.get(fileLoc))){
            stream.parallel().skip(1).forEach((value) -> {

                //Skip the first value which is nothing but the station Id and collect the rest of the values into a Set
                Set<Integer> routeSet = new HashSet<>();
                //Split the value using the delimitter and put it into an hashset for later access
                Arrays.stream(value.split(" ")).skip(1).forEach(s -> routeSet.add(Integer.parseInt(s)));
                routeMap.put(Integer.parseInt(value.split(" ")[0]), routeSet);
            });
        } catch (IOException e) {
            log.error("Unable to locate the file " + fileLoc, e);
        }
        log.info("Bus Routes are successfully loaded" + routeMap);
        busRoutes = routeMap;
    }

    /**
     * For the given dep_sid & arr_sid this method tells whether the direct route is available or not
     * @param dep_sid
     * @param arr_sid
     * @return boolean
     */
    @Override
    public boolean isBusRouteAvailable(int dep_sid, int arr_sid) {
        boolean isRouteAvailable = false;
        for (Set routeSet: busRoutes.values()){
            if (routeSet.contains(dep_sid) && routeSet.contains(arr_sid)){
                isRouteAvailable = true;
                break;
            }
        }
        log.info("Is direct bus route available for dep_sid: " + dep_sid + ", arr_sid: " + arr_sid + " ? " + isRouteAvailable);
        return isRouteAvailable;
    }
}
