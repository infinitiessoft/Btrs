#Business Trip Report System 2
---

BTRS2 is a rewrite of the original BTRS system that migrates from the Seam framework to Angularjs and JSR232 to decouple Front-end from Back- end.
Use the BTRS2 to recap the highlights of the trip, including your expected benefits, whether or not you attained them or any others, the total cost for the trip, any expected financial benefit to the company and your recommendation for the future. The more specific you can be about the benefits of your trip, the easier it is for your superiors to calculate a return on their investment.

### Release notes
Check them here: [Release notes](https://github.com/infinitiessoft/Btrs/blob/master/RELEASENOTES.md)

###USAGE
The Complete instructions for using AttendanceSystem is found [here](http://infinitiessoft.github.io/Btrs/)

###INSTALLATION
1. The Btrs can be downloaded from the [Release](https://github.com/infinitiessoft/Btrs/releases)
2. Extracting the source from the Btrs tarball is a simple matter of untarring:

  ```
  $ tar xvf btrs-X.X.X.tar
  ```
3. Editing the configuration files under `PREFIX/WEB-INF/system.properties`
  ```
  $ vi PREFIX/WEB-INF/system.properties
  ```
  Attributes:
  - pageSize: The size of a page of output.
  - smtp.host: Server that will send to email.
  - smtp.port: Port to connect to the SMTP server.
  - smtp.username: SMTP username. 
  - smtp.password: SMTP password.
  - mail.url: the link added in email send by the Attendance System that is used to link the Attendance System website.
  - mail.header: the email header.
  - mail.footer: the email footer.
  - google.calendar.service.account.p12: The Google calendar service account credential.
  - google.calendar.application.name: The name of the client application accessing the Google Calendar service.
  - google.calendar.service.account.email: The email address to Google Calendar account.
  - google.calendar.account.user: The Google Calendar service account.
  - google.calendar.id: The Google Calendar service account id.
  - db.username: The database username.
  - db.password: The database password.
  - db.driver: The driver used to connect to database.
  - db.url: database: The address point to the database to which you with to connect.

4. Moving the Attendance System .war directory to `%CATALINA_HOME%\webapps`
  ```
  $ mv attendance.war %CATALINA_HOME%\webapps
  ```

###BACKUP
1. Shutdown tomcat:

  ```
  $ systemctl stop tomcat
  ```
2. Backup database `btrs2`:

  ```
  $ pg_dump -W -U postgres -h localhost brts2 > btrs2.sql
  ```
The Complete instructions for backup database(Postgresql 9.1) can be found [here](https://www.postgresql.org/docs/9.1/static/backup.html)
3. Backup attendance war directory:

  ```
  $ tar cvf attendance.tar %CATALINA_HOME%\webapps\btrs.war
  ```
4. Start tomcat:

  ```
  $ systemctl start tomcat
  ```

###RESTORE
1. Shutdown tomcat:

  ```
  $ systemctl stop tomcat
  ```
2. Restore database `btrs2`:

  ```
  $ dropdb  -W -U postgres -h localhost btrs2 
  $ createdb -W -U postgres -h localhost btrs2
  $ psql  -W -U postgres -h localhost btrs2 < btrs2.sql
  ```
The Complete instructions for restore database(Postgresql 9.1) can be found [here](https://www.postgresql.org/docs/9.1/static/backup.html)
3. Restore attendance war directory:

  ```
  $ tar xvf attendance.tar
  $ rm -R -f %CATALINA_HOME%\webapps\btrs.war
  $ mv attendance.war %CATALINA_HOME%\webapps
  ```
4. Start tomcat:

  ```
  $ systemctl start tomcat
  ```
  
###ACKNOWLEDGEMENTS
The Attendance system relies upon these free and openly available projects:

####BACKEND
- [Jersey](https://jersey.java.net/)
- [Spring security](http://projects.spring.io/spring-security/)
- [Spring framework](https://projects.spring.io/spring-framework/)
- [Spring data jpa](http://projects.spring.io/spring-data-jpa/)
- [Hibernate](http://hibernate.org/orm/)
- [Spring retry](https://github.com/spring-projects/spring-retry)
- [Google client api](https://developers.google.com/api-client-library/java/)

####FRONTEND
- [Angularjs](https://angularjs.org/)
- [Angular-formly](http://angular-formly.com/)
- [Smart-table](http://lorenzofox3.github.io/smart-table-website/)
- [SB Amdin Angular](http://startangular.com/product/sb-admin-angular-theme/)

####REFERENCE
- [Spring Security and Angular JS](https://spring.io/guides/tutorials/spring-security-and-angular-js/)
- [angular-rest-springsecurity](https://github.com/philipsorst/angular-rest-springsecurity)
- [Attendance System](https://github.com/infinitiessoft/AttendenceSystem)
- [Btrs](https://github.com/NameFILIP/btrs)

