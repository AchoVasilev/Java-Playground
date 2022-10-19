package bg.softuni.mobilelele.services.brand;

import bg.softuni.mobilelele.model.dto.brand.BrandViewModel;

import java.util.List;

public interface IBrandService {
    List<BrandViewModel> getAllBrands();
}
