package com.samsonan.bplaces.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsonan.bplaces.dao.ImageDao;
import com.samsonan.bplaces.model.Image;
import com.samsonan.bplaces.service.ImageService;

@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {
 
    @Autowired
    ImageDao dao;
 
    public Image findById(int id) {
        return dao.findById(id);
    }
 
    public List<Image> findAll() {
        return dao.findAll();
    }
 
    public List<Image> findAllByPlaceId(int placeId) {
        return dao.findAllByPlaceId(placeId);
    }
     
    public void saveImage(Image image){
        dao.saveOrUpdate(image);
    }
 
    public void deleteById(int id){
        dao.deleteById(id);
    }
     
}