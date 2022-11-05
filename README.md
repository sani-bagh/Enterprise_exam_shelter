Endpoints:
Before accessing any shelter endpoint you need to authenticate first. Authentication:
Via postman: POST : http://localhost:8080/api/authentication Body:
{
"email": "admin@admin.com",
"password": "pirate" }
Then you can access all animals in the shelter by
• GET: http://localhost:8080/api/shelter/all Adding new animals
• •
POST: http://localhost:8080/api/shelter/new
  Example body for postman:
 {
 "animalType": "cat",
 "animal_name": "Fluffy",
 "vaccinated": true,
 "birthDate": "2020-04-04",
 "cageNr": 555
}
• Example response: {
}
Accessing to a specific animal by id
• •
   GET: http://localhost:8080/api/shelter/{id}
  Example http://localhost:8080/api/shelter/2
  Response:
  {
 "animalId": 2,
 "animalType": "cat",
 "animal_name": "Fluffy",
 "vaccinated": true,
 "birthDate": "2020-04-04",
 "registered": "2022-04-27",
 "cageNr": 555
}
Updating animal by a given id
•
 PUT: http://localhost:8080/api/shelter/{id}
  Example: http://localhost:8080/api/shelter/3
  Body:
  {
 "animalType": "dog",
 "animal_name": "new snickers",
 "vaccinated": true,
 "birthDate": "2020-05-04",
 "cageNr": 345
}
"animalId": 2,
 "animalType": "cat",
 "animal_name": "Fluffy",
 "vaccinated": true,
 "birthDate": "2020-04-04",
 "registered": "2022-04-27",
 "cageNr": 555

Response:
{ "animalId": 3,
"animalType": "dog", "animal_name": "new snickers", "vaccinated": true,
"birthDate": "2020-05-04", "registered": "2022-04-26", "cageNr": 345
}
Deleting animal by a given id
•
You can create new user with: POST: http://localhost:8080/api/register
Body example:
{
}
Response:
{
          DELETE: http://localhost:8080/api/shelter/{id}
  Example: http://localhost:8080/api/shelter/2
  Response: 200OK
     "email": "unicorn@gmail.com",
"password": "pirate"
   "userId": 8,
"email": "unicorn@gmail.com",
"created": "2022-04-27T02:07:30.7601991",
  
"password": "$2a$10$fIcVz.2ACiN0cwhSysXjk.Y2ZCqLicalxFdQOz4i9q.X2cJVTrw3y", "enabled": true,
"authorities": [
{
"id": 1,
"name": "USER" }
] }
![image](https://user-images.githubusercontent.com/77112342/200129045-d514b5a6-5753-457b-957c-24d606768c79.png)
