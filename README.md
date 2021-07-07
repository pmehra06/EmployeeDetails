# Employee Portal

Trigger a build by running the following command:
```maven
mvn clean install
```

It will generate a .war file in web-server module.
To run from command-line, execute the following command:
```
cd web-server
mvn spring-boot:run
```

The application runs on http://localhost:2222/ . The REST APIs are listed on http://localhost:2222/swagger-ui.html .

Note: The port can be configured in application.properties file.

First create an employee by clicking the Add Button in the top-right corner.
Currently, the employees list is not getting refreshed , so after creating / editing an employee , we need to manually refresh the page.

To Update/Delete the employee information, click on the pencil (edit) icon and cross (delete) icon.

Sort table by clicking the header name in ascending / descending order.