# Easypeasy Application
Created with Spring Framework and Gradle.

# 1. General info

### OOP and Design principles
In the OOP part, we have made our code and modules loosely coupled by using interfaces and service layers.
```
M: Molde —— This where we manged our Data and Logic implementation 
V: View —- This is our front end
C: Controller  —- This orchestrate users request
```

### Tech information
``` 
Pattern:Spring MVC
FrameWork: Spring boot
DB: MYSQL with JPA and Hibernate
Security: Spring Security module with authentication
Front end: thymeleaf
FrameWork: BootStrap
Test: Junit with Mockito
Build: Devbot
Versioning: git
Repository: Github
``` 



# 2. Repository Structure
```bash
easypeasy
├── src
   ├── main
           ├──java
                ├──com.alemira.sit.easypeasy
                        ├──controllers
                                ├── IndexController.java
                                ├── RecipeController.java
                                ├── UserController.java      
                        ├──config
                                ├──SQLiteDialect.java
                                ├──SQLiteIdentityColumnSupport.java
                                ├──SecurityCofiguration.java
                        ├── dao
                                ├── UserDao.java
                        ├──domain
                                ├── DomainObject.java
                                ├── LoginModel.java
                                ├── Recipe.java
                                ├── UserDomain.java
                                ├── UserLoginModel.java
                        ├── entities
                                ├── Role.java
                                ├── User.java
                        ├──services
                                ├── AbstractMapService.java
                                ├── CRUService.java
                                ├── RecipeService.java
                                ├── RecipeServiceimpl.java
                                ├── UserService.java
                                ├── UserServiceimpl.java
                                ├── UserServices.java
                        ├──EasyPeasyApplication
   ├── resources
        ├──static/image
                ├── easypeasylogo1.png
                ├── food1.jpeg
                ├── food2.jpeg
        ├── style
           ├── style.css
        ├── templates
                ├── user
                        ├── list.html
                        ├── registration.html
                        ├── user.html
                        ├── userList.html
                        ├── userform.html
                ├── index.html
                ├── login.html
                ├── recipe.html
                ├── recipeform.html
                ├── recipes.html
   ├── test
             ├──java
                ├──com.alemira.sit.easypeasy
                        ├──controllers
                                ├──EasyPeasyApplication

```

# 3. Running locally
Port Number 8001
1) Clone the latest version of this repository
``` git clone https://github.com/ztaye3/easypeasy.git ``` (_note: your credentials are needed_)

2) Open the project in Intellij IDEA and/or build the project using Gradle

3) Run the application and visit http://localhost:8001/

To signUp use:
http://localhost:8001/user/signUp




