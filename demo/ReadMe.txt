INSTALLATION & EXECUTION
------------------------
 
 * This application is a Springboot application containing an embedded H2 database. You just have to execute it
 using the following command in order to launch the server :
 
   - mvn clean spring-boot:run
   
 * At execution, the application will add two user with only the mandatory fields added. Their data are as follow :
   - id=1, username=Jean Michel, birthdate=Tue Dec 05 11:32:59 CET 1995, country=France, gender=null, phoneNumber=null
   - id=2, username=Jean Jacques, birthdate=Wed Dec 05 11:32:59 CET 1984, country=Belgium, gender=null, phoneNumber=null
   
 * you'll find in the postman directory some postman template that will allow you to call the main methods of this
 application : there's only one call per type of method.
 
IDEAS FOR ENHANCEMENT
---------------------
 * To enhance the application, I would have liked to add a DB object like a Country to add a code, name, phone prefix correspondance with the user's country. this could have allowed the possibility to limit the countries that could be inputed in the application.
 * I would have also liked to add an auto trimming on each fields in order to prevent the input of data with trailing whitespaces.