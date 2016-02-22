package com.samsonan.bplaces.service;

import java.util.List;

import com.samsonan.bplaces.model.Image;


public interface ImageService {
 
    Image findById(int id);
 
    List<Image> findAll();
     
    List<Image> findAllByPlaceId(int id);
     
    void saveImage(Image image);
     
    void deleteById(int id);
}