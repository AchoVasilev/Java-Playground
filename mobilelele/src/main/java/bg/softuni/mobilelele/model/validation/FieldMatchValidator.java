package bg.softuni.mobilelele.model.validation;

import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstField;
    private String secondField;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        var firstValue = beanWrapper.getPropertyValue(this.firstField);
        var secondValue = beanWrapper.getPropertyValue(this.secondField);

        var isValid = false;

        if (firstValue == null) {
            isValid = secondValue == null;
        } else {
            isValid = firstValue.equals(secondValue);
        }

        if (!isValid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}
