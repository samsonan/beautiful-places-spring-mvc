package com.samsonan.bplaces.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
 
import com.samsonan.bplaces.model.FileBucket;;
 
@Component
public class FileValidator implements Validator {
     
    public boolean supports(Class<?> class_) {
        return FileBucket.class.isAssignableFrom(class_);
    }
 
    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;
         
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}