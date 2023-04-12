
# API REST "Laptops" desarollada en Spring Boot:
  (App para control de ordenadotres portatiles)

1. Se inyectaron las dependencias en el "inicializer de Spring"; DevTools, Web, Data JPA, H2, Spring Security como 
   consta en el fichero pom.xml. Posteriormente se agrego dependencia "Spring Security". Las cuales nos proveen de 
   herramientas tales como Build project automatically; para recargar el proyecto cada vez que se realiza un cambio 
   (Settings/Build, Execution and Deployment/Compiler/), herramientas para el desarrollo web, trabajo con BB.DD, y 
   seguridad/firewall.
2. La app consta de la clase LaptopControllers (@RestControllers) y otra clase ejemplo HelloControllers (Ejemplo) con un
   metodo "saludo()" con un endpoint (/saludo) que retorna un String con un mensaje saludando e indicando en que perfil
   trabajamos dev o test.
   En LaptopControllers podemos encontrar los metodos para realizar las peticiones HTTP de la API, a traves de un 
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

3. ENDPOINTS:
   1.     http://localhost:8080/saludo
   2.                          /api/laptops (findAll(), buscar todas las laptops)                                  
   3.                          /api/laptop/id (findOneById(), deleteById(); buscar una por id o borrar una por id)
   4.                          /api/laptop (update(), actualizar dato de una laptop enviando id en el body)
   5.                          /api/delete-all (deleteAll(), borra todos ) 
   
4. En la ruta /src/tets/java/.com.example.controller, encontramos en  la clase LaptopControllersTest con los test unitarios.
5. Se creo un fichero system.properties, en la raiz del proyecto, donde se especifica version de Java; 17. 
6. se crearon 2 "profiles" (perfiles): appilcation-dev.properties; para el "modo desarrollador" y uno mas, 
   application-test.properties para gestionar los test. Los mismos se pueden activar desde un tercer fichero; 
   application.properties, donde ademas de puede configurar el puerto de acceso a la API, configuracion de BB.DD., 
   entre otras. (/src/main/resources).
7. Se creo un fichero ".gitignore" con el cual se gestionan la publicacion de archivos (ignorados). (Ubicacion: raiz).
8. Se importo fichero "Spring API REST Laptop.postman_collection.json" con las pruebas realizadas sobre Postman. (raiz).
9. Se implemento Swagger-ui; (clase com.example.config.SwaggerConfig). Se incluyeron ademas herramientas que proporciona
   "Spring Fox", anotaciones (@ApiModel/@ApiOperation/@ApiParam) y "logs" que mejoran la documentacion de la API.
10. Se configuro VCS (control de versiones) y se compartio el proyecto creando un repo en GitHub.

11. SPRING SECURITY:
    Se realizaron pruebas con la dependencia Spring Security desde explorador de internet y desde Postman, verificando
    funcionamiento de la clave autogonerada por Spring con usuario user. Se crearon usuario y password especificos desde
    fichero application.properties (user: ariel, password: 12345) y se verifico nuevamente el funcionamiento. 
    Se crearon dos perfiles de acceso; ADMIN, USER cada uno con sus respectivos password (12345, 1234). se crearon 
    tambien politicas  o permiso/restricciones de acceso para cada endpoint y un ejemplo de configuracion de Firewall
    (comentado) que permite el uso de ";" sobre la direccion de solicitud del mismo.
    Recordar borrar cookies en Postman antes de una nueva prueba, las configuraciones deben relizarse en "Authorization",
    Basic Auth; alli configurar usuario y contraseña.
    El orden de gerarquia de los medios dde autenticacion son; en principio la clave aleatoria que genera Spring al 
    momento de ejecutar la app, posteriormente cualquier indicacion que se especifique en al fichero 
    application.porpperties y por ultimo lo que se indique en el metodo configure(AuthenticationManagerBuilder auth)
    heredando de la clase WebSecurityConfigurerAdapter (deprecated luego de Spring Boot 2.5.5, se puede usar igual)
    POLITICAS DE ACCESO: 
    1.     http://localhost:8080/saludo (sin restricciones/ sin auth)
    2.                          /api/laptops (user comun y password)                                  
    3.                          /api/laptop/id (user)
    4.                          /api/laptop (user)
    5.                          /api/delete-all (restringido, admin con password) 

13. La app se compilo con Apache Maven mediante el cual se genero el archivo .jar

14. 
Deployment on Repl.it:
[![Run on Repl.it](https://replit.com/badge/github/Ariel15682/laptops)](https://replit.com/new/github/Ariel15682/laptops)


