# Interactive Query Visualizer Web Service

## Server used for deployment:
Apache Tomcat 8.5

## Starting the web service:
1. The file InteractiveQueryVisualizerWS.war must be deployed on a server running at port 8080
   For Apache Tomcat the file can be dropped at \webapps directory of its installation.

## Web service responses in JSON format:
1. http://192.168.42.16:8080/InteractiveQueryVisualizerWS/webapi/lookupviews - returns a list of all lookup views
2. http://192.168.42.16:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/customer_list - return a given view (customer_list)
3. http://192.168.42.16:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/customer_list/select?attributes=ID,name - 
   returns the attributes (ID, name) of the given view (customer_list)
4. http://192.168.42.16:8080/InteractiveQueryVisualizerWS/webapi/lookupviews/customer_list/select?attributes=ID,name&orderBy=name:asc - 
   returns the attributes (ID, name) of the given view (customer_list), ordered by name in ascending order
