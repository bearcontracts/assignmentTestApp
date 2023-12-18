**Technologies Used:**
Server Framework: SpringBoot
Database: PostgreSQL
Language: Java

**How to Use:**
Access each endpoint using appropriate HTTP methods
Follow the specified request and response parameter formats.
Use the token received from /token method in the header for authorized access.

**Registration**

URL: /registration
Parameters:
username 
password 
Response:
status 
message 

**Authentication**

URL: /token
Parameters:
username 
password 
Response:
token 
balance 
games 
status 
message 

**Deposit**

URL: /deposit
Parameters:
username 
amount 
Response:
balance 
deposit_id 
status 
message 

**Refund**

URL: /rollback
Parameters:
deposit_id 
Response:
status 
balance 
message 

**Game Creation**

URL: /game/create
Parameters:
name 
title 
price 
Response:
status 
game_id 
message 

**Purchasing a Game**

URL: /game/buy
Parameters:
game_id 
username 
Response:
status 
game_id 
balance 
message 
