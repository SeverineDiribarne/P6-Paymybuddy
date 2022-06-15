# Pay my buddy
Use Case Diagram
#
<img src="https://user-images.githubusercontent.com/75072149/167119364-3e764ab2-4ffa-4b66-9bce-e815ff3fab19.png" width="60%"></img>
#
Class Diagram  
#
<img src="https://user-images.githubusercontent.com/75072149/173115591-044661fc-e104-47bd-bb63-b9ddcb662fd1.png" width="60%"></img> 
#
Database modeling schema
#
<img src="https://user-images.githubusercontent.com/75072149/171674113-e9f97ca4-74ca-4629-a974-94e70f7eb364.png" width="60%"></img> 

# Users email and password
#
email : aurelie.dupont@gmail.com
password : ABC123
#
email : harry.martin@gmail.com
password : DEF456
#
email : marion.deltiny@gmail.com
password : GHI789
#
email : marina.dupond@gmail.com
password : BCD234
#
email : olivier.herriberry@gmail.com
password : EFG567
#
email : pierre.albane@gmail.com
password : IJH890
#
email : severine.diribarne@gmail.com
password : AZE987
#
email : enzo.smith@gmail.com
password : RTY645
#
email : babou.bibi@gmail.com
password : LMN123

# Application.properties
#
Create this file into src/main/resources.
Here is the contain example :
#
spring.datasource.url=jdbc:mysql://localhost:3306/db-paymybuddy
spring.datasource.username= your root username 
spring.datasource.password= your root password

spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
spring.thymeleaf.prefix=file:src/main/resources/templates/

spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=none
