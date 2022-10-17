package bg.softuni.mobilelele.model.dto.offer;

import bg.softuni.mobilelele.model.enums.EngineEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddOfferInputModel {
    @NotNull
    private EngineEnum engine;

    @NotEmpty
    private String imageUrl;

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
}
