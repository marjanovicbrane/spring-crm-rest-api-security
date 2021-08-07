# SPRING SECURITY CRM REST API with AUTHENTICATION in memory and  AUTHORIZATION

I made CRM REST API with authentication in memory.User can have 3 roles:employee, manager and admin.

![image](https://user-images.githubusercontent.com/61464267/128601283-6d8a2ef0-05e9-4e11-a085-80f67f1192fa.png)

I secured all REST endpoints and adds following security authorizations:
EMPLOYEE role can perform GET http request method, 
MANAGER can perform GET, POST and PUT http request methods and 
ADMIN can perform GET, POST, PUT and DELETE http request methods, ie. All CRUD methods.

On the following picture we can see the same this example using REST CLIENT POSTMAN with POST http request method.
In the section Authorization we also added username and password in POSTMAN,  it is obvious that this user has role MANAGER or ADMIN because we are using here POST http request method.

![image](https://user-images.githubusercontent.com/61464267/128601316-ac591964-84dc-46e3-b34b-0f1106c60e54.png)



