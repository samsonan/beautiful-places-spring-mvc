package com.samsonan.bplaces.web;


import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
 
import com.samsonan.bplaces.model.ImageUploadForm;
import com.samsonan.bplaces.model.Image;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.service.ImageService;
import com.samsonan.bplaces.service.PlaceService;
import com.samsonan.bplaces.util.validation.ImageFormValidator;

/**
 * Plug-in used for preview: http://plugins.krajee.com/file-input#features
 * @author ShamanXXI
 *
 */
@Controller
public class MediaController {
 
    private static String UPLOAD_LOCATION="C:/bplaces/images/";
 
    private static final Logger log = LoggerFactory.getLogger(MediaController.class);
    
    @Autowired
    ImageService imageService;

    @Autowired
    PlaceService placeService;
    
    @Autowired
    ImageFormValidator fileValidator;
 
    @InitBinder("imageForm")
    protected void initBinderImageForm(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
     
    @RequestMapping(value = { "/places/add-image-{placeId}" }, method = RequestMethod.GET)
    public String addImages(ModelMap model, @PathVariable int placeId) {

    	Place place = placeService.findById(placeId);
        model.addAttribute("place", place);
        
    	ImageUploadForm imageForm = new ImageUploadForm();
    	imageForm.setTitle(place.getTitle());
        model.addAttribute("imageForm", imageForm);
 
        List<Image> images =  imageService.findAllByPlaceId(placeId);
        model.addAttribute("images", images);
         
        return "places/manage_images";
    }

    /**
     * 
     * Edit function will be added later, or not
     * 
    @RequestMapping(value = { "/places/edit-image-{placeId}-{imageId}" }, method = RequestMethod.GET)
    public String editImage(ModelMap model, @PathVariable int placeId, @PathVariable int imageId) throws IOException {

    	Place place = placeService.findById(placeId);
        model.addAttribute("place", place);

    	Image image = imageService.findById(imageId);

    	ImageUploadForm imageForm = new ImageUploadForm();
    	imageForm.setTitle(image.getTitle());
    	imageForm.setDescription(image.getDescription());
		imageForm.setEditImageSrc(image.getImageSrc());

    	if (image.getContentType().equals(Image.CONTENT_TYPE_URL)) { 
    		imageForm.setImageUrl(image.getFilename());
    	}
    	model.addAttribute("imageForm", imageForm);
 
        List<Image> images =  imageService.findAllByPlaceId(placeId);
        model.addAttribute("images", images);
        
        return "manage_images";
    }

    @RequestMapping(value = { "places/edit-image-{placeId}-{imageId}" }, method = RequestMethod.POST)
    public String editImage(@ModelAttribute("imageForm") @Valid ImageUploadForm imageForm, BindingResult result, ModelMap model, @PathVariable int placeId) throws Exception{
         
        if (result.hasErrors()) {

        	Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            List<Image> image = imageService.findAllByPlaceId(placeId);
            model.addAttribute("image", image);
             
            return "manage_images";
        } else {
             
            Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            if (!imageForm.getFile().isEmpty())
            	saveImageFromFile(imageForm, place);
            else if (!imageForm.getImageUrl().isEmpty())
            	saveImageFromUrl(imageForm, place); //TODO: check if available
            else //TODO: correct exception
            	throw new Exception();
 
            return "redirect:/places/add-image-"+placeId;
        }
    }
    
    */
    
    @RequestMapping(value = { "/places/delete-image-{placeId}-{imageId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int placeId, @PathVariable int imageId) {
        imageService.deleteById(imageId);
        return "redirect:/add-image-"+placeId;
    }  
    
    @RequestMapping(value = { "/places/add-image-{placeId}" }, method = RequestMethod.POST)
    public String uploadImage(@ModelAttribute("imageForm") @Valid ImageUploadForm imageForm, BindingResult result, ModelMap model, @PathVariable int placeId) throws Exception{
         
        if (result.hasErrors()) {

        	Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            List<Image> image = imageService.findAllByPlaceId(placeId);
            model.addAttribute("image", image);
             
            return "places/manage_images";
        } else {
             
            Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            if (!imageForm.getFile().isEmpty())
            	saveImageFromFile(imageForm, place);
            else if (!imageForm.getImageUrl().isEmpty())
            	saveImageFromUrl(imageForm, place); //TODO: check if available
            else //TODO: correct exception
            	throw new Exception();
 
            return "redirect:/places/add-image-"+placeId;
        }
    }    

    private void saveImageFromUrl(ImageUploadForm imageForm, Place place) throws IOException{
    	
        Image image = new Image();
        image.setTitle(imageForm.getTitle());
        image.setDescription(imageForm.getDescription());
        image.setContentType("URL");
        image.setFilename(imageForm.getImageUrl());
        image.setPlace(place);
        imageService.saveImage(image);
    	
    }
    
    private void saveImageFromFile(ImageUploadForm imageForm, Place place) throws IOException{
        
        Image image = new Image();
         
        MultipartFile multipartFile = imageForm.getFile();
         
        image.setDescription(imageForm.getDescription());
        image.setTitle(imageForm.getTitle());
        image.setContentType(multipartFile.getContentType());
        image.setContent(multipartFile.getBytes());//saving content to DB - not used now
        
        /* Saving to file */
        
        String newFilenameBase = UUID.randomUUID().toString();
        String originalFileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String newFileName = newFilenameBase + originalFileExtension;
        image.setFilename(newFileName);
        
        File newFile = new File(UPLOAD_LOCATION + "/" + newFileName);
        try {
        	multipartFile.transferTo(newFile);
            
            BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
            String thumbnailFileName = newFilenameBase + "-thumbnail.png";
            File thumbnailFile = new File(UPLOAD_LOCATION + "/" + thumbnailFileName);
            ImageIO.write(thumbnail, "png", thumbnailFile);
            
            image.setThumbnailFileName(thumbnailFileName);
            
        } catch(IOException e) {
            log.error("Could not upload file "+multipartFile.getOriginalFilename(), e);
        }        
        
        
        image.setPlace(place);
        imageService.saveImage(image);
    }    
 
}
