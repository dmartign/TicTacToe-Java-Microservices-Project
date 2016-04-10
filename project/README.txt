   _____                          ____                                         
  / ___/__  ______  ___  _____   / __ \__  ______  ___  _____                  
  \__ \/ / / / __ \/ _ \/ ___/  / / / / / / / __ \/ _ \/ ___/                  
 ___/ / /_/ / /_/ /  __/ /     / /_/ / /_/ / /_/ /  __/ /                      
/____/\__,_/ .___/\___/_/     /_____/\__,_/ .___/\___/_/                       
  _______ /_/      ______               _///__                __________  ____ 
 /_  __(_)____    /_  __/___ ______    /_  __/___  ___  _    /_  __/ __ )/ __ \
  / / / / ___/_____/ / / __ `/ ___/_____/ / / __ \/ _ \(_)    / / / __  / / / /
 / / / / /__/_____/ / / /_/ / /__/_____/ / / /_/ /  __/      / / / /_/ / /_/ / 
/_/ /_/\___/     /_/  \__,_/\___/     /_/  \____/\___(_)    /_/ /_____/_____/  
Super Duper version of tic-tac-toe that runs in your browser ontop of a set
of microservices that facilitate gameplay, chatting, and logins.

Security was not a major concern when generating this application so passwords
are not to be considered securely stored, transmitted or handled at all.
Don't enter a password you care about.

Currently it only works with all services and clients connecting from localhost.

Requirements:
Versions listed are what we tested on.
Windows
	10
Mongodb
	3.2
Tomcat
	8.0.32
A modern web browser
	Chrome - 49.0.2623.110 m
	Firefox - 45.0.1
Apache Maven
	3.3.9
Some free time

Running:
1) Start Mongo on your localhost at port 27017 with no credentials
	This should be the default configuration, you may need to tell it where to
	store the data with the --dbpath <path> command line argument
2) run "java -jar AuthenticationService-0.0.1-SNAPSHOT.jar"
3) run "java -jar GameServer-0.0.1-SNAPSHOT.jar"
4) run "node Chat\index.js"
5) start tomcat
6) deploy gateway.war to tomcat
7) Navigate to localhost:8080/gateway
8) Default login credentials are Player1:password and Player2:password you can
also register a new user
**Note** The game database resets at each run to clear clutter

To edit:
change the code
use maven to build Authentication and GameServer
you can import gateway into eclipse and reexport it from there