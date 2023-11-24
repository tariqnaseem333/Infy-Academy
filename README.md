# Infy-Academy
### It is a maven project based on Java using various concepts of OOPs and having structure of Enterprise Application. 
### Infy Academy is an online academy which invites candidates to participate in various events, and also provides grades for each candidate based on his/her performance.

#### The different steps taking place in the project are:- 
1.) User inputs the data. This data is sent to the Tester class. In this Project, there is no User Interface the data is directly inserted in Tester Class.\
2.) The Tester class converts the raw data to a proper Model class object and send the object to the Service Class.\
3.) The Service class sends the object to the Validator class to get the inputs validated. If the inputs are in valid format, the Validator returns a successful response, else a failure response.\
4.) The Service class add candidates to database( there is no database only message is displayed) and get grades for all candidates sent to Tester Class.\
5.) The Tester class then displays this output to the User.
