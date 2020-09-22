package training.employees;

import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !Strings.isBlank(value) &&
                value.length() > 2 &&
                value.length() <= maxLength &&
                Character.isUpperCase(value.charAt(0));
    }

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }
}
