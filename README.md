# busRouteChallenge


Environment used for development: Mac, Java 8, Spring, Maven, Junit

The spring boot spawns a tomcat container and the port number is specified in resources/application.properties
The request are served through BusRouteController class.
By default the Bus Route data file (BusRoutes.txt) located under resources will be loaded. This can be overriden by passing the filelocation as parameter while starting the application

