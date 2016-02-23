package com.samsonan.bplaces.util.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
 
import com.samsonan.bplaces.model.ImageUploadForm;;
 
@Component
public class FileValidator implements Validator {
     
    public boolean supports(Class<?> class_) {
        return ImageUploadForm.class.isAssignableFrom(class_);
    }
 
    public void validate(Object obj, Errors errors) {
        ImageUploadForm file = (ImageUploadForm) obj;
         
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}