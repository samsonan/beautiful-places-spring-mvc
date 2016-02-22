package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.model.PlaceLink;
import com.samsonan.bplaces.dao.AbstractDao;
import com.samsonan.bplaces.dao.ImageDao;
import com.samsonan.bplaces.model.Image;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

@Repository("imageDao")
public class ImageDaoImpl extends AbstractDao<Serializable, Image> implements ImageDao {
	
	public void save(Image image) {
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
		Image image =  getByKey(id);
        delete(image);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Image> findAllByPlaceId(int placeId) {
		
//		Criteria criteria = createEntityCriteria();
//      criteria.add(Restrictions.eq("place.id", placeId));
		
		Query query = getSession().createQuery("from Image i where i.place.id=:id");
		query.setInteger("id", placeId);
        return (List<Image>) query.list();
	}
} 
