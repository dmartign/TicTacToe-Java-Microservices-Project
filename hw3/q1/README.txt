CSE551 Home Assignment 3

This application will allow a user to register, login, and see some select research previously done by the authors of the application.

A non-authorized user trying to access a .jsp or .do endpoint will be redirected to the login page where they can either register or present their credentials.

In the event of an error, an error page will be displayed that will inform the user that an error occurred and present a link to the login page. An easy way to invoke an error is to attempt to access a random URL and get a 404 Not Found.

The application uses log4j2 to log some informational and debugging statements to the console. All logging is appended to a file located at C:\temp\hw3q1.log. This can be modified in the log4j2.xml file located in resources. This will likely error on a Unix based computer as it logs to C:\

SETUP:
There is a sql script in the db folder that needs to be run to initialize the database. This creates the database and creates the users table and inserts some initial users.

The web.xml file needs to be edited to include the credentials used to access the locally hosted database.

Tested on Tomcat7 & Tomcat8, MySQL 5.7.10, mysql-connector 5.1.38 (included), log4j2 2.5 (included), Windows 10, Windows 8, Java8, Google Chrome 49.0.2623.87, Firefox 45.0.1