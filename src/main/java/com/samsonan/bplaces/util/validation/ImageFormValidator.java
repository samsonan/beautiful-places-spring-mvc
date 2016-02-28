package com.samsonan.bplaces.util.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import com.samsonan.bplaces.model.ImageUploadForm;;
 
@Component
public class ImageFormValidator implements Validator {
     
    public boolean supports(Class<?> class_) {
        return ImageUploadForm.class.isAssignableFrom(class_);
    }
 
    public void validate(Object obj, Errors errors) {
        ImageUploadForm file = (ImageUploadForm) obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.image.title");

        if(file.getFile().isEmpty() && file.getImageUrl().isEmpty()){
        	errors.rejectValue("file", "missing.form.file.url");
        }
        
        if(!file.getFile().isEmpty()){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.form.file");
            }
        }
    }
}