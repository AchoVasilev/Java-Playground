package bg.softuni.mobilelele.model.dto.model;

public class ModelViewModel {
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public ModelViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
