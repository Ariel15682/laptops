package com.example.controller;

import com.example.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllersTest {

    private TestRestTemplate testRestTemplate;


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Testeo metodo findAll que busca todos los articulos/instancias de tipo Laptop" +
            "desde controladores Spring REST")
    @Test
    void findAll() {
        // ResponseEntity; respuesta HTTP, permite utilizar metodos http
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        // Comprobando aserciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        // El array Laptop[] es en formato JSON, se debe pasar a array Java y luego una List
        // El array se arma obteniendo los body de cada laptop y lo pasamos a List
        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println("Cantidad de laptops: " + laptops.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/5", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    /**
     * Preparar las cabeceras
     * Preparar el JSON
     * Preparar la peticion
     * Ejecutar la peticion solicitando que te devuelva un dato de tipo Laptop
     */
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                        "manufacturer": "Lenovo",
                        "modell": "IdesaPad3 15ABA7",
                        "colour": "Gris",
                        "screen": "15,6",
                        "keyLanguage": "Eng",
                        "processor": "AMD Ryzen 3",
                        "memmoryGb": 8,
                        "ssd": true,
                        "diskCapacityGb": 256,
                        "operativeSystem": "Windows 10",
                        "situation": "Abierta sin usar",
                        "price": 245.20
                }
                     """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Lenovo IdesaPad3 15ABA7", result.getManufacturer().concat(" ").concat(result.getModell()));
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                        "manufacturer": "Lenovo",
                        "modell": "IdesaPad3 15ABA7",
                        "colour": "Gris",
                        "screen": "15,6",
                        "keyLanguage": "Eng",
                        "processor": "AMD Ryzen 3",
                        "memmoryGb": 8,
                        "ssd": true,
                        "diskCapacityGb": 256,
                        "operativeSystem": "Windows 10",
                        "situation": "Abierta sin usar",
                        "price": 245.20
                }
                     """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //Se crea Laptop
        testRestTemplate.exchange("/api/laptop", HttpMethod.POST, request, Laptop.class);

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        System.out.println("Laptop creada en UPDATE Spring Boot Random Id 1 " + response.getBody());

        json = """
               {
                        "id": 1,
                        "manufacturer": "Lenovo",
                        "modell": "IdesaPad3 15ABA7",
                        "colour": "Gris",
                        "screen": "15,6",
                        "keyLanguage": "Eng",
                        "processor": "AMD Ryzen 3",
                        "memmoryGb": 8,
                        "ssd": true,
                        "diskCapacityGb": 256,
                        "operativeSystem": "Windows 10",
                        "situation": "Abierta sin usar (Modificada desde Spring Test UPDATE)",
                        "price": 245.20
                }
               """;

        request = new HttpEntity<>(json, headers);

        response = testRestTemplate.exchange("/api/laptop", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals("Abierta sin usar (Modificada desde Spring Test UPDATE)", result.getSituation());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deleteById() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                        "manufacturer": "Lenovo",
                        "modell": "IdesaPad3 15ABA7",
                        "colour": "Gris",
                        "screen": "15,6",
                        "keyLanguage": "Eng",
                        "processor": "AMD Ryzen 3",
                        "memmoryGb": 8,
                        "ssd": true,
                        "diskCapacityGb": 256,
                        "operativeSystem": "Windows 10",
                        "situation": "Abierta sin usar",
                        "price": 245.20
                }
                     """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity response = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        System.out.println("Id 1 vacio: " + response.getBody());

        //Se crea Laptop
        testRestTemplate.exchange("/api/laptop", HttpMethod.POST, request, Laptop.class);

        response = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        System.out.println("Laptop creada para ser borrada en Spring Boot (DELETE) Id 1 Random: " + response.getBody());

        response = testRestTemplate.exchange("/api/laptop/1", HttpMethod.DELETE, request, Laptop.class);
        System.out.println("Se borro Laptop: " + response.getBody());

        response = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteAll() {
    }
}