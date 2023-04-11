package com.example.controller;

import com.example.entities.Laptop;
import com.example.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopControllers {

    private final Logger log = LoggerFactory.getLogger(LaptopControllers.class); //permite hacer logs

    // ATRIBUTOS
    private LaptopRepository laptopRepository;

    // CONSTRUCTOR
    public LaptopControllers(LaptopRepository laptopRepository) {this.laptopRepository = laptopRepository;}

    // METODOS CRUD (entidad Laptop)

        //Buscar todos los libros(Lista) (GET)
    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        //Recuperar y mostrar los libros (devuelve todos los libros)
        return laptopRepository.findAll();
    }

        // Buscar una laptop por id
    /**
     * El metodo findOneById() de la instancia bookRepository devuelve un objeto Optional
     * El metodo isPresent() de instancias Optional busca la entidad
     * con ese id en el repositorio y devuelve un boolean
     * @param id
     * @return
     */
    @GetMapping("/api/laptop/{id}")
    @ApiOperation("Buscar un articulo Laptop por clave primaria id Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        /**
         * si la entidad "esta presente" obtenemos el body con el metodo get()
         * Devolvemos la ResponseEntity con el metodo ok(body)
         */
        if(laptopOpt.isPresent())
            // Codigo 200 status ok
            return ResponseEntity.ok(laptopOpt.get());
        else
            // Metodo notFound() retorna un header builder y con ese metodo invocamos a metodo build() que devuelve una ResponseEntity
            // Un notFound() devuelve valor 404, no vacio o null
            return ResponseEntity.notFound().build();
    }

        // Crear/Guardar una nueva laptop en BB.DD pasada por parametro (POST)
    @PostMapping("/api/laptop")
    @ApiOperation("Crear un nuevo articulo Laptop")
    //public Laptop create(@RequestBody Laptop laptop){
        //return laptopRepository.save(laptop);

    // Usamos ResponseEntity para poder hacer verificaciones y hacer mas robusto el metodo
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));

        if(laptop.getId() != null){
            // Si la laptop existe quiere decir que no es una creacion
            log.warn("Trying to create an existent laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

        //Actualizar un libro

    /**
     * Postman: Se debe pasar una laptop completa en el metodo PUT
     * @param laptop
     * @return
     */
    @PutMapping("/api/laptop")
    @ApiOperation("Actualizar articulo Laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if(laptop.getId() == null) {
            // Si la laptop no existe quiere decir que es una creacion
            log.warn("Trying to update a null id or none existent laptop");
            return ResponseEntity.badRequest().build();
        }

        if(!laptopRepository.existsById(laptop.getId())) {
            // Esto verifica si nos pasan un numero que no existe
            log.warn("Trying to update a none existent id book");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

        //Borrar un libro
    @DeleteMapping("/api/laptop/{id}")
    @ApiOperation("Borrar articulo Laptop por id")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a none existent book");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        // Retorna status "no contiene" el libro
        return ResponseEntity.noContent().build();
    }

        //Borrar todos los libros
    @DeleteMapping("/api/laptops")
    @ApiIgnore // etiqueta/anotacion Swagger para ignorar/esconder un metodo
    public ResponseEntity<Laptop> deleteAll() {
        if (laptopRepository.count() > 0){
            laptopRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }else{
            System.out.println("No hay articulos para borrar");
            return ResponseEntity.noContent().build();
        }
    }

}
