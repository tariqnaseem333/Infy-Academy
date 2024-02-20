# Infy-Academy
## It is a Java Enterprise Application maven based project using various concepts of OOPs and Java API(Regex API, DateTime API, Collection Framework). 
### Infy Academy is an online academy which invites candidates to participate in various events, and also provides grades for each candidate based on his/her performance. In this project, two operations are performed
### 1.) Adding candidates
### 2.) Getting candidate reports

#### The different steps taking place in the application are explained below:- 
##### 1.) User inputs the data in Tester Class (Presentation Tier). In this project, the client tier is not used. the inputs will be taken directly in the Tester Class.
##### 2.) The Tester class sends the Model class object to the Service Class (Business Tier).
##### 3.) The Service class sends the object to the Validator class to get the inputs validated. 
##### 4.) If the inputs are in valid format, the data is send to the DAO class (Persistence Tier) to store it in the database. In this project, the database is not being used simply a messsage will be returned from the database.
##### 4.) The Service class also performs operations like calculating grade and getting candidates report from DAO class (Persistence Tier). In this project, the database is not being used a hard coded data will be returned.
##### 5.) Based on the responses from the Validator and the DAO classes, the Service class formulates  either a successful output or a failure output and return the same to the Tester class.
##### 6.) The Tester class then displays the output to the User.
