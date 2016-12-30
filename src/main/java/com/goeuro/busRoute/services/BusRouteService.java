package com.goeuro.busRoute.services;

/**
 * Created by ganeshnagarajan on 12/28/16.
 */
public interface BusRouteService {
    boolean isBusRouteAvailable(int dep_sid, int arr_sid);
}
