/**
 *
 */
package cn.shikl.data.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import cn.shikl.data.execption.ValidatorException;

/**
 * 验证实体的类.jsr303验证validator.
 *
 * @param <T> 泛型参数.
 * @author shikl.
 * @version 1.0
 */
@Service(value = "validatorEntity")
public class ValidatorEntity<T> implements IValidator<T> {

    /**
     * 验证器.
     */
    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /* (non-Javadoc)
     * @see IValidator#validate()
     */
    @Override
    public void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            String validateError = "";
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                validateError += constraintViolation.getPropertyPath() + constraintViolation.getMessage() + ";";
            }
            throw new ValidatorException(validateError);
        }
    }

}
