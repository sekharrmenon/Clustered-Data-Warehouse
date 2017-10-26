# Clustered-Data-Warehouse
 To run this project
 
-clone the project to desktop

-make sure maven,mysql is installed

-run the datawarehouse.sql to create the schema

-if the default username password change for mysql please update the property file in the classpath

-got to the pom directory of the cloned project and run mvn clean install to generate the .war file

- deploy the war file in (tomcat directory)/webapps and start the server

-look for localhost:8080/datawarehouse/ for the welcome url

- upload the mysample,csv and check for the results.If everything works fine you will see uploaded succesfull message in the UI

-For errors please check for warehouse.log inside log folder of tomcat directory

