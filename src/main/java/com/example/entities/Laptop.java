package com.example.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Laptops")
@ApiModel("Entidad Laptop para representar un elemento de tipo computadora portátil con peso y tamaño ligero")
public class Laptop {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave identificatoria autogenerada")
    private Long id;
    private String manufacturer;
    private String modell;
    private String colour;
    private String screen;
    private String keyLanguage;
    private String processor;
    private Integer memmoryGb;
    private Boolean ssd;
    private Integer diskCapacityGb;
    private String operativeSystem;
    private String situation;
    private Double price;

    // CONSTRUCTORES
        //vacio
    public Laptop() { }

        //Sobrecargado

    public Laptop(Long id, String manufacturer, String modell, String colour, String screen, String keyLanguage,
                  String processor, Integer memmoryGb, Boolean ssd, Integer diskCapacityGb, String operativeSystem,
                  String situation, Double price){
        this.id = id;
        this.manufacturer = manufacturer;
        this.modell = modell;
        this.colour = colour;
        this.screen = screen;
        this.keyLanguage = keyLanguage;
        this.processor = processor;
        this.memmoryGb = memmoryGb;
        this.ssd = ssd;
        this.diskCapacityGb =diskCapacityGb;
        this.operativeSystem = operativeSystem;
        this.situation = situation;
        this.price = price;
    }

    // GETTERS/SETTERS
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getManufacturer() {return manufacturer;}

    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}

    public String getModell() {return modell;}

    public void setModell(String modell) {this.modell = modell;}

    public String getColour() {return colour;}

    public void setColour(String colour) {this.colour = colour;}

    public String getScreen() {return screen;}

    public void setScreen(String screen) {this.screen = screen;}

    public String getKeyLanguage() {return keyLanguage;}

    public void setKeyLanguage(String keyLanguage) {this.keyLanguage = keyLanguage;}

    public String getProcessor() {return processor;}

    public void setProcessor(String processor) {this.processor = processor;}

    public Integer getMemmoryGb() {return memmoryGb;}

    public void setMemmoryGb(Integer memmoryGb) {this.memmoryGb = memmoryGb;}

    public Boolean getSsd() {return ssd;}

    public void setSsd(Boolean ssd) {this.ssd = ssd;}

    public Integer getDiskCapacityGb() {return diskCapacityGb;}

    public void setDiskCapacityGb(Integer diskCapacityGb) {this.diskCapacityGb = diskCapacityGb;}

    public String getOperativeSystem(){return operativeSystem;}

    public void setOperativeSystem(String operativeSystem) {this.operativeSystem = operativeSystem;}

    public String getSituation(){return situation;}

    public void setSituation(String situation) {this.situation = situation;}

    public Double getPrice(){return price;}

    public void setPrice(Double price) {this.price = price;}

    //toString()
    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", modell='" + modell + '\'' +
                ", colour='" + colour + '\'' +
                ", screen='" + screen + '\'' +
                ", keyLanguage='" + keyLanguage + '\'' +
                ", processor='" + processor + '\'' +
                ", memmoryGb=" + memmoryGb +
                ", ssd=" + ssd +
                ", diskCapacityGb=" + diskCapacityGb +
                ", operativeSystem='" + operativeSystem + '\'' +
                ", situation='" + situation + '\'' +
                ", price=" + price +
                '}';
    }
}
