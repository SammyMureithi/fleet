clone the project 
git clone https://github.com/SammyMureithi/fleet.git

open the application.properties found in /main/resources

change this line "spring.datasource.url=jdbc:postgresql://localhost:5430/shipping"
to accomodate the port number of your postgress


that is  ~~ spring.datasource.url=jdbc:postgresql://localhost:{{portNumber}}/shipping

lastly create the a table called shipping

run your project