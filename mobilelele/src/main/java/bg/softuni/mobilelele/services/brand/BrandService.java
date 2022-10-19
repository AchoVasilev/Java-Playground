package bg.softuni.mobilelele.services.brand;

import bg.softuni.mobilelele.model.dto.brand.BrandViewModel;
import bg.softuni.mobilelele.model.dto.model.ModelViewModel;
import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.repositories.IBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {
    private final IBrandRepository brandRepository;

    public BrandService(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandViewModel> getAllBrands() {
        return this.brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());
    }

    private BrandViewModel mapBrand(BrandEntity brandEntity) {
        var models = brandEntity
                .getModels()
                .stream()
                .map(this::mapModel)
                .toList();

        var result = new BrandViewModel()
                .setModels(models)
                .setName(brandEntity.getName());

        return result;
    }

    private ModelViewModel mapModel(ModelEntity modelEntity) {
        return new ModelViewModel()
                .setId(modelEntity.getId())
                .setName(modelEntity.getName());
    }
}
