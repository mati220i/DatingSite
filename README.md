# DatingSite

# [PL]
Aplikacja serwera będąca celem wykonania pracy inżynierskiej, w której należało wykonać algorytm automatycznego kojarzenia par. W aplikacji zostało przygotowane kila funkcji głównych oraz pobocznych. Z dostępnych funkcjonalności udostępniony jest panel logowania, rejestracji, główny z podziałem na sekcje dla użytkowników i administratora, panel ustawień, powiadomień, znajomych, skrzynka z wiadomościami, panel wyświetlania profilu użytkownika. Aplikacja została napisana przy użyciu Spring Boot i Spring Data Jpa. Do przechowywania danych została użyta baza danych Oracle.

# [EN]
The server application which is the purpose of the engineering work in which the algorithm of automatic pairing should have been performed. In the application, several main and side functions have been prepared. The available functionalities include the login and registration panel, the main one divided into sections for users and administrators, the settings panel, notifications, friends, the message box, the user profile display panel. The application was written using Spring Boot and Spring Data Jpa. The Oracle database was used for data storage.


## Endpoints

### Activation Code

**PUT** /activationCode/addCode
<br>
body:[ActivationCode]

**DELETE** /activationCode/deleteCode
<br>
body:[ActivationCode]

**POST** /activationCode/checkCode
<br>
body:[ActivationCode]

### City

**GET** /city/getByName
<br>
parameters: name - City name
<br>
return: list of cities

**GET** /city/getCities
<br>
return: list of cities

### Database Generator

**POST** /databaseGenerator/generatePrepare
<br>
body:[City]

**GET** /databaseGenerator/generate
<br>
parameters: quantity - quantity of generated users

**POST** /databaseGenerator/generate
<br>
parameters: quantity - quantity of generated users
<br>
body:[City]

### Friends

**GET** /friends/count
<br>
parameters: username - username's friends

**PUT** /friends/sendInvitation
<br>
parameters: from, to

**PUT** /friends/undoInvitation
<br>
parameters: from, to

**PUT** /friends/cancelInvitation
<br>
parameters: from, to

**PUT** /friends/acceptInvitation
<br>
parameters: from, to

**PUT** /friends/removeFriend
<br>
parameters: from, to

**GET** /friends/getFriends
<br>
parameters: username
<br>
returns:  lisf of user friends

### Messages

**GET** /messages/getConversation
<br>
parameters: username
<br>
returns:  lisf of user conversations

**PUT** /messages/newMessage
<br>
body: MessageHelper
<br>
returns:  lisf of user conversations

**PUT** /messages/readConversation
<br>
parameters: whose, fromWho


### Notificaitons

**GET** /notification/count
<br>
parameters: username

**GET** /notification/getAll
<br>
parameters: username
<br>
returns:  lisf of user notifications

**PUT** /notification/read
<br>
parameters: id

**DELETE** /notification/delete
<br>
body: Notification

**PUT** /notification/newWave
<br>
parameters: from, to

**PUT** /notification/newKiss
<br>
parameters: from, to

### User

**GET** /user/registration/checkLogin/{login}
<br>
parameters: login

**GET** /user/registration/checkEmail/{email}
<br>
parameters: email


**PUT** /user/registration/addUser
<br>
body: User

**POST** /user/getUser
<br>
parameters: login
<br>
returns: User

**POST** /user/getUserWithAllData
<br>
parameters: login
<br>
returns: User

**PUT** /user/updateUser
<br>
body: User
<br>
returns: User

**PUT** /user/changePassword
<br>
body: User
<br>
returns: User

**DELETE** /user/deleteUser
<br>
body: username

**POST** /user/getUsers
<br>
body: SearchHelper
<br>
returns: list of Users

**GET** /user/getUsers
<br>
returns: list of users

**GET** /user/getRole
<br>
parameters: username
<br>
returns: user role

**GET** /user/getFitUsers
<br>
parameters: username - user who make request, real - flag, that demand real users
<br>
body: body
<br>
returns: ret

**GET** /user/setAdminRole

### Test
**GET** /test
