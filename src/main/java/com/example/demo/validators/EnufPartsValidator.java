package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        if(context!=null)myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        if (product.getId() != 0) {
            Product myProduct = repo.findById((int) product.getId());
            for (Part p : myProduct.getParts()) {
                if (p.getInv()<(product.getInv()-myProduct.getInv()))return false;
            }

            // New validation: Ensure part inventory remains within the min and max range
            for (Part p : myProduct.getParts()) {
                int adjustedInv = p.getInv() - (product.getInv() - myProduct.getInv());

                if (adjustedInv < p.getMinInv()) {
                    // Add error message for inventory falling below the minimum
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate(
                            "Reducing product inventory will cause part '" + p.getName() +
                                    "' to fall below the minimum inventory level (" + p.getMinInv() + ")."
                    ).addConstraintViolation();

                    return false;
                }

                if (adjustedInv > p.getMaxInv()) {
                    // Add error message for inventory exceeding the maximum
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate(
                            "Reducing product inventory will cause part '" + p.getName() +
                                    "' to exceed the maximum inventory level (" + p.getMaxInv() + ")."
                    ).addConstraintViolation();

                    return false;
                }
            }

            return true; // Validation passed
        } else {
            return true; // Skip validation for new products
        }
    }
}