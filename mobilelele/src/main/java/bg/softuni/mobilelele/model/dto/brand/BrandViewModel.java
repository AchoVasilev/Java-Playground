package bg.softuni.mobilelele.model.dto.brand;

import bg.softuni.mobilelele.model.dto.model.ModelViewModel;

import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {
    public BrandViewModel() {
        this.models = new ArrayList<>();
    }

    private String name;

    private long Id;

    private List<ModelViewModel> models;

    public String getName() {
        return name;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return Id;
    }

    public BrandViewModel setId(long id) {
        Id = id;
        return this;
    }

    public List<ModelViewModel> getModels() {
        return models;
    }

    public BrandViewModel setModels(List<ModelViewModel> models) {
        this.models = models;
        return this;
    }

    public BrandViewModel addModel(ModelViewModel model) {
        this.models.add(model);

        return this;
    }
}
