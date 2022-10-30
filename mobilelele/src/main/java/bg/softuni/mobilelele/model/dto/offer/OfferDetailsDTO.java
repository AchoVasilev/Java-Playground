package bg.softuni.mobilelele.model.dto.offer;

import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class OfferDetailsDTO {
    private String imageUrl;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private EngineEnum engine;
    private TransmissionEnum transmission;

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDetailsDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDetailsDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailsDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferDetailsDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferDetailsDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferDetailsDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }
}
