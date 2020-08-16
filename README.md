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
 
 # Testing the application using Postman
 
 the root url for the product web service is "http://localhost:8080/api/product".
 
 ## creating a new product
 You may create a new product using Postman by sending a POST request with the following JSON in the Request body to "http://localhost:8080/api/product/":
 ```json
{
  "name": "My 1st product name",
  "price": 25.00
}
```
and will receive the following response:

```json
{
    "id": 1,
    "name": "My 1st product",
    "price": 12.23,
    "createdAt": "2020-08-16T12:39:18.485+00:00"
}
```
As you can see, the field "id" and "createdAt" have been added. The field "createdAt" cannot be altered and any attempt to do so will be ignored.

## listing all products

is as simple as issuing a GET request to "http://localhost:8080/api/product/".

## retrieving a specific product with a known ID

issue a GET request to "http://localhost:8080/api/product/id" where the id represents the object's know identifier.

## updating an existing object.

issue a PUT request to "http://localhost:8080/api/product/" with the complete JSON of your object. The value for "createdAt" cannot be changed.

## deleting an object with a known id:

issue a DELETE request to "http://localhost:8080/api/product/id" where the id represents the object's know identifier.

