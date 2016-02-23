package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.ImageDao;
import com.samsonan.bplaces.model.Image;

@Repository("imageDao")
public class ImageDaoImpl extends AbstractDao<Serializable, Image> implements ImageDao {
	
	public void saveOrUpdate(Image image) {
		//TODO: update functionality
		persist(image);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Image> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Image>) criteria.list();
	}
	
	@Override
	public Image findById(int id) {
		return getByKey(id);
	}
	
	@Override
	public void deleteById(int id) {
        delete(getByKey(id));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Image> findAllByPlaceId(int placeId) {
		
		Query query = getSession().createQuery("from Image i where i.place.id=:id");
		query.setInteger("id", placeId);
        return (List<Image>) query.list();
	}
} 
