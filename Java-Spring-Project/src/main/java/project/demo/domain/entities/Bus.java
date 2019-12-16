package project.demo.domain.entities;

import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Bus extends BaseEntity{

    private String manufacturer;

    private String title;

    private String description;

    private String model;

    private BigDecimal price;

    private Fuel fuel;

    private Integer year;

    private Status status;

    private Byte[] image;

    public Bus() {
    }

    @Column(name = "brand",nullable = false)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String brand) {
        this.manufacturer = brand;
    }

    @Column(name = "model",nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "price",nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel",nullable = false)
    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Column(name = "year",nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @Column(name = "image")
    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
