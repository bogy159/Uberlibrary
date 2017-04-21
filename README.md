### Uberlibrary 

This is the root folder of the project **Uberlibrary**

## backend
The **backend** is implemented in *Java* and has following jobs:
 - getting data from *Wienbibliothek* and make it available for *webui*
 - saving the *reviews*
 - make the *reviews* available for the *webui*

Preconditions:
- install [mongodb](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
- run the *backend* from *Intellij* by simply starting the *SpringBoot Application* or by maven command `mvn clean compile exec:java -Dexec.mainClass="at.tuwien.innovation.group7.Application"`

## webui
The **webui** is implemented in *JavaScript* and uses the framework *AngularJs*
- install [node](https://nodejs.org/en/download/) on your machine
- *sudo npm install -g angular-cli*
- *sudo npm install -g bower*
- go into the folder and run `npm install` and `bower install`
- run the *webui* with `ng serve -host 0.0.0.0`