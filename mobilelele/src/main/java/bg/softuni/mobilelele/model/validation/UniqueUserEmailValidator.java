package bg.softuni.mobilelele.model.validation;

import bg.softuni.mobilelele.repositories.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    private final IUserRepository userRepository;

    public UniqueUserEmailValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository
                .findByEmail(value)
                .isEmpty();
    }
}
