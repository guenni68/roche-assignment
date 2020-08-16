This repo contains the java code for the roche application home assignment.

# Instructions to run and test the code, using docker.

* cd into the top level directory of the project
* execute the command "docker-compose up -d"
* execute the command "docker attach maven". This will connect you to the running bash within the maven container.
* within the container execute "mvn clean install". Running this command for the 1st time will take some time to complete as all maven dependencies need to be downloaded.
* (optional) run the command "mvn clean test" to run the tests.
* execute the command "mvn spring-boot:run -pl product-service-web". This will start the web service which can be reached at "http://localhost:8080/api/product/".

 
 You may use the tool postman to interact with and test the applications functionality.
 
 
 When you are finished review the application you may "detach" from the container by using "CTL+P CTL+Q", on a Mac.
 Once back in the host window you can then issue the command "docker-compose down", which will terminate and garbage collect the container(s).
 
 