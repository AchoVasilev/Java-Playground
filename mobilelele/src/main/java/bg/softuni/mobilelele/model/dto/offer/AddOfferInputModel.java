package bg.softuni.mobilelele.model.dto.offer;

import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AddOfferInputModel {

    @NotNull
    @Min(1)
    private Long modelId;

    @NotNull
    private EngineEnum engine;

    @NotNull
    private TransmissionEnum transmission;

    @NotEmpty
    private String imageUrl;

    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @Positive
    @NotNull
    private Integer mileage;

    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferInputModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferInputModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferInputModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public AddOfferInputModel setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferInputModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public AddOfferInputModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferInputModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public AddOfferInputModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }
}
