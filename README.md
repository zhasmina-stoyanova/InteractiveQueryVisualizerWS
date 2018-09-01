# Interactive Query Visualizer Web Service

## Server used for deployment:
Apache Tomcat 8.5

## Starting the web service:
1. The file InteractiveQueryVisualizerWS.war must be deployed on a server running at port 8080
   For Apache Tomcat the file can be dropped at \webapps directory of its installation.

## Web service requests in JSON format:
The web service allows users to:
Get a list of all lookup views available for the role of the logged in user
URL	http://localhost:8080/InteractiveQueryVisualizerWS/webapi/lookupviews

Get table data for the specified lookup view
URL	http://localhost:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/ /{lookupview}

Get a list of attributes for a given lookup view
URL	http://localhost:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/ /{lookupview}/attributes

Get the min and max values of a given attribute
URL	http://localhost:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/ /{lookupview}/extremes

Get table data for the specified lookup view, attributes, where clause and orderBy attribute
URL	http://localhost:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/ /{lookupview}/select



