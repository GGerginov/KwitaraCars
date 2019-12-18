package project.demo.domain.entities;

import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Car extends BaseEntity{

    private String manufacturer;

    private String title;

    private String description;

    private String model;

    private BigDecimal price;

    private Fuel fuel;

    private Integer year;

    private Status status;

    private String location;

    private Integer millage;

    private String imageUrl;

    private User user;

    public Car() {
    }

    @Column(name = "brand")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String brand) {
        this.manufacturer = brand;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
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

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "millage")
    public Integer getMillage() {
        return millage;
    }

    public void setMillage(Integer millage) {
        this.millage = millage;
    }

    @Column(name = "image_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
