package com.schoolstuff;

import org.apache.wicket.validation.CompoundValidator;
import org.apache.wicket.validation.validator.PatternValidator;

/**
 * Created by Madis on 06/01/2017.
 */

public class SearchValidator extends CompoundValidator<String>
{
    //Parent class CompoundValidator implements java.io.serializable interface and for that reason we need to declare ID to avoid potential incompatibility issues.
    private static final long serialVersionUID = 1L;

    public SearchValidator()
    {
        //Checks whether only numbers are used. IValidationError will be reported if input is invalid. Only 1 or more numbers are allowed without whitespace.
        add(new PatternValidator("[0-9]+"));
    }
}
