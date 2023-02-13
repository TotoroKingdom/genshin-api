package com.totoro.annonation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public class UniqueCheckValidator implements ConstraintValidator<UniqueCheck, Object> {

    private String id;
    private String field;
    private String tips;

    @Override
    public void initialize(UniqueCheck uniqueCheck) {
        this.id  = uniqueCheck.id();
        this.field = uniqueCheck.field();
        this.tips = uniqueCheck.tips();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return UniqueCheckUtils.fieldRepeat(id, field, o, tips);
    }
}
