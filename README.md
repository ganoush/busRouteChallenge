# busRouteChallenge


Environment used for development: Mac, Java 8, Spring, Maven, Junit

The spring boot spawns a tomcat container and the port number is specified in resources/application.properties
The request are served through BusRouteController class. 

The Bus route data file location must be passed as a command line argument to start the application.
sample url: http://localhost:8088/api/direct?dep_sid=1&arr_sid=2
