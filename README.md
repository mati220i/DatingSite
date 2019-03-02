# DatingSite

# [PL]
Aplikacja serwera będąca celem wykonania pracy inżynierskiej, w której należało wykonać algorytm automatycznego kojarzenia par. W aplikacji zostało przygotowane kila funkcji głównych oraz pobocznych. Z dostępnych funkcjonalności udostępniony jest panel logowania, rejestracji, główny z podziałem na sekcje dla użytkowników i administratora, panel ustawień, powiadomień, znajomych, skrzynka z wiadomościami, panel wyświetlania profilu użytkownika. Aplikacja została napisana przy użyciu Spring Boot i Spring Data Jpa. Do przechowywania danych została użyta baza danych Oracle.

# [EN]
The server application which is the purpose of the engineering work in which the algorithm of automatic pairing should have been performed. In the application, several main and side functions have been prepared. The available functionalities include the login and registration panel, the main one divided into sections for users and administrators, the settings panel, notifications, friends, the message box, the user profile display panel. The application was written using Spring Boot and Spring Data Jpa. The Oracle database was used for data storage.


## Endpoints

### Activation Code

Adds the activation code
<br>
**PUT** /activationCode/addCode
<br>
body: ActivationCode


Removes the activation code
<br>
**DELETE** /activationCode/deleteCode
<br>
body: ActivationCode


Checks the activation code
<br>
**POST** /activationCode/checkCode
<br>
body: ActivationCode



### City

Gets the city by name
<br>
**GET** /city/getByName
<br>
parameters: name - City name
<br>
return: list of cities


Gets all cities
<br>
**GET** /city/getCities
<br>
return: list of cities




### Database Generator

Generates prepared data
<br>
**POST** /databaseGenerator/generatePrepare
<br>
body: City


Generates data
<br>
**GET** /databaseGenerator/generate
<br>
parameters: quantity - quantity of generated users


Generates data
<br>
**POST** /databaseGenerator/generate
<br>
parameters: quantity - quantity of generated users
<br>
body: City



### Friends

Counts the number of invitations to friends
<br>
**GET** /friends/count
<br>
parameters: username - username's friends


Sends an invitation to friends
<br>
**PUT** /friends/sendInvitation
<br>
parameters: from, to


Revokes an invitation to friends
<br>
**PUT** /friends/undoInvitation
<br>
parameters: from, to


Cancels invitation to friends
<br>
**PUT** /friends/cancelInvitation
<br>
parameters: from, to


Accepts an invitation to friends
<br>
**PUT** /friends/acceptInvitation
<br>
parameters: from, to


Removes a person from friends
<br>
**PUT** /friends/removeFriend
<br>
parameters: from, to


Retrieves a list of friends
<br>
**GET** /friends/getFriends
<br>
parameters: username
<br>
returns:  lisf of user friends




### Messages

Gets a list of conversations
<br>
**GET** /messages/getConversation
<br>
parameters: username
<br>
returns:  lisf of user conversations


Creates a new message
<br>
**PUT** /messages/newMessage
<br>
body: MessageHelper
<br>
returns:  lisf of user conversations


Marks the message as read
<br>
**PUT** /messages/readConversation
<br>
parameters: whose, fromWho


### Notificaitons

Counts the number of notifications
<br>
**GET** /notification/count
<br>
parameters: username


Gets all notifications
<br>
**GET** /notification/getAll
<br>
parameters: username
<br>
returns:  lisf of user notifications


Sets the notification as read
<br>
**PUT** /notification/read
<br>
parameters: id


Removes the notification
<br>
**DELETE** /notification/delete
<br>
body: Notification


Sends a wave of hand
<br>
**PUT** /notification/newWave
<br>
parameters: from, to


Sends a kiss
<br>
**PUT** /notification/newKiss
<br>
parameters: from, to




### User


Check if login exists
<br>
**GET** /user/registration/checkLogin/{login}
<br>
parameters: login


Check if e-mail exist
<br>
**GET** /user/registration/checkEmail/{email}
<br>
parameters: email


Adding new User
<br>
**PUT** /user/registration/addUser
<br>
body: User


Get user by login
<br>
**POST** /user/getUser
<br>
parameters: login
<br>
returns: User


Getting user with all data by login
<br>
**POST** /user/getUserWithAllData
<br>
parameters: login
<br>
returns: User


Updates the user
<br>
**PUT** /user/updateUser
<br>
body: User
<br>
returns: User


Changes the user's password
<br>
**PUT** /user/changePassword
<br>
body: User
<br>
returns: User


Removes the user
<br>
**DELETE** /user/deleteUser
<br>
body: username


Gets the list of users
<br>
**POST** /user/getUsers
<br>
body: SearchHelper
<br>
returns: list of Users


Gets the list of users
<br>
**GET** /user/getUsers
<br>
returns: list of users


Retrieves the list of user roles
<br>
**GET** /user/getRole
<br>
parameters: username
<br>
returns: user role


Gets users along with information about matching a given user
<br>
**GET** /user/getFitUsers
<br>
parameters: username - user who make request, real - flag, that demand real users
<br>
body: body
<br>
returns: ret


Sets the administrator role
<br>
**GET** /user/setAdminRole

### Test

Test the connection
<br>
**GET** /test
