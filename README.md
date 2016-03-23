# beautiful-places-spring-mvc 

Ongoing project based on:
- Spring MVC 4 (+security)
- Hibernate (although with some direct JDBC)
- PostgreSQL
- Gradle as build tool
- Mockito, DBUnit, etc for unit/integration testing

Additinal features include:
 - Velicity Engine/Template
 - Ehcache for ref data
 - Email sendlist

UI is simply JSP/jstl with a bunch of JavaScript/jQuery. And some ajax, obviously.

And Bootstrap 3 for CSS

Since front-end is not my strong point I used following amazing plugins:
- For map and sidepanels: (https://github.com/Turbo87/sidebar-v2)
- For image upload: (http://plugins.krajee.com/file-input)
- multiselect bootstrap feature: http://davidstutz.github.io/bootstrap-multiselect/

Country list in xml/json was taken from this project:
https://github.com/mledoze/countries


#how to
 - database DDL scripts are in /database/DDL folder. They are PostgreSQL specific
 - ref data is in /database/DML folder
 - different environment setting are controlled via profiles:
   - "default" is for dev profile
   - "junit_test" is for unit tests
   no matter the profile, application-{profile_name}.properties should be present in /resource folder
   active profile is specified by "spring.profile.active" env.variable and app.parameter 