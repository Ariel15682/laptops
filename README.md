
# API REST "Laptops" desarollada en Spring Boot:
  (App para control de ordenadotres portatiles)

1. Se inyectaron las dependencias en el "inicializer de Spring"; DevTools, Web, Data JPA y H2 como consta en el fichero
   pom.xml. Posteriormente se agrego dependencia "Spring Security". Las cuales nos proveen de herramientas tales como 
   Buil project automatically; para recargar el proyecto cada vez que se realiza un cambio (Settings/Build, Execution
   and Deployment/Compiler/), herramientas para el desarrollo web, trabajo con BB.DD, y seguridad/firewall.
2. La app consta de la clase LaptopControllers (@RestControllers) y otra clase ejemplo HelloControllers (Ejemplo). 
   En la primera podemos encontrar los metodos para realizar las peticiones HTTP de la API, a traves de un 
   browser/Postman o Swagger (7). Los metodos CRUD (Create/Crear, Read/Leer, Update/Actualizar, Delete/Borrar) "findAll"
   (GET); para buscar todos los elementos/articulos "Laptops", "findById"(GET); para buscar un articulo especifico por 
   clave principal autogenerda (@Id/@GeneratedValue/@PathVariable), un metodo "create" (POST), uno "update" (PUT) para 
   enviar el body y dos metodos mas parar borrar (DEL); "deleteAll", que borra todos luego de comprobar la existencia de
   algun articulo y "deleteById" que borra uno en concreto proporcionando su Id. Esto es posible gracias a las 
   anotaciones "@...Mapping" que ademas asigna un direccion dentro de la URL principal ("endpoints"). 
   Encontramos tambien la Clase (@Entity) Laptop que ademas sera una tabla (@Table) dentro de la cual tenemos los 
   atributos propios de la entidad, constructores y metodos para obtener y configurar los atributos + toString().
   y por ultimo la interfaz "LaptopRepository" (@Repository) que extiende de JPARepository que brindara los metodo para
   utilizar en el repositorio ("almacen")de Laptops y la clase principal (main), "ObEj4Application" con el metodo main.
   (@SpringBootApplication) en la cual se da inicio a la aplicacion y donde se crearon  objetos de ejemplo (memoria)
3. En la ruta /src/tets/java/.com.example.controller, encontramos en  la clase LaptopControllersTest con los test unitarios.
4. Se creo un fichero system.properties, en la raiz del proyecto, donde se especifica version de Java; 17. 
5. se crearon 2 "profiles" (perfiles): appilcation-dev.properties; para el "modo desarrollador" y uno mas, 
   application-test.properties para gestionar los test. Los mismos se pueden activar desde un tercer fichero; 
   application.properties, donde ademas de puede configurar el puerto de acceso a la API, configuracion de BB.DD., 
   entre otras. (/src/main/resources).
6. Se creo un fichero ".gitignore" con el cual se gestionan la publicacion de archivos (ignorados). (Ubicacion: raiz).
7. Se importo fichero "Spring API REST Laptop.postman_collection.json" con las pruebas realizadas sobre Postman. (raiz).
8. Se implemento Swagger-ui; (clase com.example.config.SwaggerConfig). Se incluyeron ademas herramientas que proporciona
   "Spring Fox", anotaciones (@ApiModel/@ApiOperation/@ApiParam) y "logs" que mejoran la documentacion de la API.
9. Se configuro VCS (control de versiones) y se compartio el proyecto creando un repo en GitHub.
10. La app se compilo con Apache Maven mediante el cual se genero el archivo .jar

11. 
Deployment on Repl.it:
[![Run on Repl.it](https://replit.com/badge/github/Ariel15682/laptops)](https://replit.com/new/github/Ariel15682/laptops)


