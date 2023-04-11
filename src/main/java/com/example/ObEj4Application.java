package com.example;

import com.example.entities.Laptop;
import com.example.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObEj4Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObEj4Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);


		// Creacion Objetos (instancias laptop)
		Laptop laptop1 = new Laptop(null, "HP", "255 GEN 8", "Gris", "15,6", "Esp", "3020e", 8, true, 256, "Windows 11", "Nuevo", 250.00);

		Laptop laptop2 = new Laptop(null, "ASUS", "VivoBook X515JA", "Gris", "15,6", "Esp", "i3", 8, true, 256, "Windows 11", "Nuevo", 315.87);

		Laptop laptop3 = new Laptop(null, "ACER", "Aspire3 A315", "Negro", "15,6", "Esp", "celeron4000", 8, true, 256, "Windows 10", "Nuevo", 299.00);

		Laptop laptop4 = new Laptop(null, "Lenovo", "ThinkPad E490", "Negro", "14,0", "Esp", "3020e", 8, true, 256, "Windows 11", "Usado", 245.00);

		//Laptop laptop5 = new Laptop(null, "Lenovo", "IdeaPad3 15ABA7", "Gris", "15,6", "Eng", "AMD Ryzen 3", 8, true, 256, "Windows 10", "Abierto sin uso", 245.20);


		//Guardar Laptop en BB.DD
		System.out.println("Guardando laptop creada..." + repository.save(laptop1));
		System.out.println("Guardando laptop creada..." + repository.save(laptop2));
		System.out.println("Guardando laptop creada..." + repository.save(laptop3));
		System.out.println("Guardando laptop creada..." + repository.save(laptop4));


	}

}
