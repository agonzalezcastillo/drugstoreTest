# CSN
Proyecto para consultar eventos sismicos en distintos Paises

# Pasos para instalación del Proyecto!

- clonar proyecto desde git@github.com:agonzalezcastillo/drugstoreTest.git

- debes poseer Docker instalado en tu ordenador

- ir a la carpeta raiz del proyecto y ejecutar los siguientes comandos
```sh
docker build -f Dockerfile -t drugstoreav . 
```
- Generará la imagen del JAR
```sh
docker run -p 8080:8080 drugstoreav
```
- Iniciará el contenedor con la imagen generada

# Endpoints Disponibles para consulta
- consulta por nombre de comuna
```sh
[GET] localhost:8080/drugstores/district?district= buin
```
- consulta por nombre de farmacia
 ```sh
[GET] localhost:8080/drugstores/name?name=SALCOBRAND
```
- consulta por nombre de comuna y nombre de farmacia
```sh
[GET] localhost:8080/drugstores/district/name?districtName=santiago&drugstoreName=SALCOBRAND
```



