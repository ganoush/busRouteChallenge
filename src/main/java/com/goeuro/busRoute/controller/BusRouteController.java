package com.goeuro.busRoute.controller;

import com.goeuro.busRoute.dto.response.BusRouteResponse;
import com.goeuro.busRoute.services.BusRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ganeshnagarajan on 12/28/16.
 */
@RestController
@RequestMapping(value="/api")
public class BusRouteController {

    private static final Logger log = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteService busRouteService;

    /**
     *  This method returns the BusRouteResponse for the given dep_sid and arr_sid.
     *  The response tells whether there is a valid bus route available for the given station ids
     * @param dep_sid
     * @param arr_sid
     * @return BusRouteResponse
     */
    @RequestMapping(value = "/direct",  method = RequestMethod.GET)
    public BusRouteResponse searchDirectBusRoute(@RequestParam int dep_sid, @RequestParam int arr_sid){
        log.info("Request to search for direct bus route for dep_sid : " + dep_sid + " arr_sid : " + arr_sid);
        BusRouteResponse response = new BusRouteResponse();
        response.setArr_sid(arr_sid);
        response.setDep_sid(dep_sid);
        response.setDirect_bus_route(busRouteService.isBusRouteAvailable(dep_sid, arr_sid));
        log.info("Response for direct bus route search " + response);
        return response;
    }
}
