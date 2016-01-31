package com.samsonan.bplaces.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.model.Place;

@Repository("placeDao")
public class PlaceDaoImpl implements PlaceDao {
	
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }		
	public void savePlace(Place place) {
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(place);
        tx.commit();
        session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Place> getAllPlaces() {
		Session session = this.sessionFactory.openSession();

        List<Place> personList = session.createQuery("from Place").list();
        
        session.close();
        return personList;
	}
	
	@Override
	public Place findById(int id) {
		return (Place) getSession().get(Place.class, id);
	}
	
	@Override
	public void deletePlaceById(int id) {
        Query query = getSession().createSQLQuery("delete from place where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();		
	}
} 
