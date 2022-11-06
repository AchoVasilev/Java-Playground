package bg.softuni.mobilelele.utils;

import bg.softuni.mobilelele.model.entity.*;
import bg.softuni.mobilelele.model.enums.CategoryEnum;
import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.enums.UserRoleEnum;
import bg.softuni.mobilelele.repositories.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestDataUtils {
    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final IOfferRepository offerRepository;
    private final IModelRepository modelRepository;
    private final IBrandRepository brandRepository;

    public TestDataUtils(
            IUserRepository userRepository,
            IUserRoleRepository userRoleRepository,
            IOfferRepository offerRepository,
            IModelRepository modelRepository,
            IBrandRepository brandRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    public UserEntity createTestAdmin(String email) {
        this.initRoles();

        var admin = new UserEntity()
                .setEmail(email)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true)
                .setUserRoles(this.userRoleRepository.findAll());

        return this.userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {
        this.initRoles();

        var user = new UserEntity()
                .setEmail(email)
                .setFirstName("User")
                .setLastName("Userov")
                .setActive(true)
                .setUserRoles(this.userRoleRepository.findAll()
                        .stream()
                        .filter(r -> r.getUserRole() != UserRoleEnum.ADMIN)
                        .toList());

        return this.userRepository.save(user);
    }

    public OfferEntity createTestOffer(UserEntity seller, ModelEntity model) {
        var offer = new OfferEntity()
                .setEngine(EngineEnum.GASOLINE)
                .setMileage(10000)
                .setPrice(BigDecimal.TEN)
                .setDescription("Test description")
                .setYear(2009)
                .setTransmission(TransmissionEnum.MANUAL)
                .setSeller(seller)
                .setModel(model);

        return this.offerRepository.save(offer);
    }

    public BrandEntity createTestBrand() {
        var brand = new BrandEntity()
                .setName("Ford");

        return this.brandRepository.save(brand);
    }

    public ModelEntity createTestModel(BrandEntity brand) {
        var model = new ModelEntity()
                .setName("Fiesta")
                .setBrand(brand)
                .setCategory(CategoryEnum.CAR)
                .setStartYear(2000)
                .setEndYear(2011L)
                .setImageUrl("http://image.com");

        return this.modelRepository.save(model);
    }

    public void cleanUp() {
        this.offerRepository.deleteAll();
        this.userRepository.deleteAll();
        this.userRoleRepository.deleteAll();
        this.modelRepository.deleteAll();
        this.brandRepository.deleteAll();
    }

    private void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            var adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            var userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(userRole);
        }
    }
}
