##This application implement some CRUD features that will be accessed
by user authentication/authorization using Spring Security features (JWT/OAuth2).
The diagram below explains briefly how OAuth2 works using Authorization server and
Authentication server (that will provide user access by checking user roles)

![How OAuth 2.0 works](/oauth2.png)

The entities created for this project are shown below
![Class diagram](/diagrama_classe_bds4.png)

The project was developed using TDD (Test Driven Development) methodology, Junit test cases are implemented in
the following files (package _com.devsuperior.bds04.controllers_):

-   _EventControllerIT.java_
-   _CityControlerIT.java_
