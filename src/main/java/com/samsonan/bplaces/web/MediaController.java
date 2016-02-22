package com.samsonan.bplaces.web;


import java.awt.image.BufferedImage;

//https://github.com/blueimp/jQuery-File-Upload/wiki

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
 
import com.samsonan.bplaces.model.FileBucket;
import com.samsonan.bplaces.model.Image;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.service.ImageService;
import com.samsonan.bplaces.service.PlaceService;
import com.samsonan.bplaces.util.FileValidator;
import org.springframework.mock.web.MockMultipartFile;
 
//http://websystique.com/springmvc/spring-mvc-4-fileupload-download-hibernate-example/


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
    FileValidator fileValidator;
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        //binder.setValidator(fileValidator);
    }
 
    
    @RequestMapping(value = { "/places/add-image-{placeId}" }, method = RequestMethod.GET)
    public String addImages(ModelMap model, @PathVariable int placeId) {

    	Place place = placeService.findById(placeId);
        model.addAttribute("place", place);
        
    	FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
 
        List<Image> images =  imageService.findAllByPlaceId(placeId);
        model.addAttribute("images", images);
         
        return "manage_images";
    }

    @RequestMapping(value = { "/places/edit-image-{placeId}-{imageId}" }, method = RequestMethod.GET)
    public String editImage(ModelMap model, @PathVariable int placeId, @PathVariable int imageId) throws IOException {

    	Place place = placeService.findById(placeId);
        model.addAttribute("place", place);

    	Image image = imageService.findById(imageId);

    	FileBucket fileModel = new FileBucket();
    	fileModel.setDescription(image.getDescription());

    	if (image.getContentType().equals("URL")) { //TODO: constant
        	fileModel.setImageUrl(image.getFilename());
    	} else {
        	File file = new File(UPLOAD_LOCATION+image.getFilename());
        	
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
            		image.getFilename(), image.getContentType(), IOUtils.toByteArray(input));    	
        	
        	fileModel.setFile(multipartFile);
    	}
        model.addAttribute("fileBucket", fileModel);
 
        List<Image> images =  imageService.findAllByPlaceId(placeId);
        model.addAttribute("images", images);
        
        return "manage_images";
    }

    @RequestMapping(value = { "/places/delete-image-{placeId}-{imageId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int placeId, @PathVariable int imageId) {
        imageService.deleteById(imageId);
        return "redirect:/add-image-"+placeId;
    }  
    
    @RequestMapping(value = { "/places/add-image-{placeId}" }, method = RequestMethod.POST)
    public String uploadImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int placeId) throws IOException{
         
        if (result.hasErrors()) {
            System.out.println("validation errors");
            Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            List<Image> image = imageService.findAllByPlaceId(placeId);
            model.addAttribute("image", image);
             
            return "manage_images";
        } else {
             
            System.out.println("Fetching file");
             
            Place place = placeService.findById(placeId);
            model.addAttribute("place", place);
 
            if (!fileBucket.getFile().isEmpty())
            	saveImageFromFile(fileBucket, place);
            else if (!fileBucket.getImageUrl().isEmpty())
            	saveImageFromUrl(fileBucket, place); //TODO: check if available
            else //TODO: exception
            	throw new RuntimeException();
 
            return "redirect:/places/add-image-"+placeId;
        }
    }    

    private void saveImageFromUrl(FileBucket fileBucket, Place place) throws IOException{
    	
        Image image = new Image();
        image.setDescription(fileBucket.getDescription());
        image.setContentType("URL");
        image.setFilename(fileBucket.getImageUrl());
        image.setPlace(place);
        imageService.saveImage(image);
    	
    }
    
    private void saveImageFromFile(FileBucket fileBucket, Place place) throws IOException{
        
        Image image = new Image();
         
        MultipartFile multipartFile = fileBucket.getFile();
         
//        image.setName(multipartFile.getOriginalFilename());
        image.setDescription(fileBucket.getDescription());
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
//            image.setSize(mpf.getSize());
//            image.setThumbnailSize(thumbnailFile.length());
            
        } catch(IOException e) {
            log.error("Could not upload file "+multipartFile.getOriginalFilename(), e);
        }        
        
        
        image.setPlace(place);
        imageService.saveImage(image);
    }    
 
}
