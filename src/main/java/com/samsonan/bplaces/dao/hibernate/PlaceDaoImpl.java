package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.PlaceDao;
import com.samsonan.bplaces.dao.UserDao;
import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;
import com.samsonan.bplaces.model.User;


@Repository("placeDao")
public class PlaceDaoImpl extends AbstractDao<Serializable, Place> implements PlaceDao {

	@Autowired
	UserDao userDao;
	
	public void saveOrUpdate(Place place) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user = userDao.findByName(name);
		
		if (place.getId() != null) {
			place.setUpdated(new Date());
			place.setUpdatedBy(user);
			merge(place);
		} else {
			place.setUpdated(new Date());
//			place.setCreated(new Date()); done on DB level
			place.setUpdatedBy(user);
			place.setCreatedBy(user);
			persist(place);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Place> findAll(PlaceFilters filters) {
		//select * from place p inner join place_type pt on p.id=pt.place_id where pt.type in (N1,N2,N3,C1,C2) 
		// [and p.unesco = true]

		//HQL will look like this! Note that it is not fully compliant with Criteria!!!!
		//Query query = getSession().createQuery("from Place p inner join p.placeTypes pt where pt in ('VILLAGE') ");
		
		Criteria criteria = createEntityCriteria();

		if (filters != null) {
			criteria.createAlias("placeTypes", "pt");
			
			String[] resList = (String[]) ArrayUtils.addAll( filters.getCulturalTypes(),  filters.getNaturalTypes());
			if (resList.length==0) resList = new String [] {""};//at least one element should be in the array
			criteria.add(Restrictions.in("pt.elements", resList));
			
			if (filters.getCountries() != null && filters.getCountries().length > 0)
				criteria.add(Restrictions.in("country.codeIso2Char", filters.getCountries()));
			else if (filters.getRegion() != null) {
				DetachedCriteria regionCountriesSubquery = DetachedCriteria.forClass(Country.class, "c");
						// Filter the Subquery
						Criterion cr1 = Restrictions.eq("subRegionCode", filters.getRegion());//SEA/NE/WAF/etc
						Criterion cr2 = Restrictions.eq("regionName", filters.getRegion()); //not a mistake, can also be Africa/Europe/etc
						regionCountriesSubquery.add(Restrictions.or(cr1,cr2))
					    // SELECT The country code  
					    .setProjection( Projections.property("c.codeIso2Char") );
				criteria.add( Subqueries.propertyIn("country", regionCountriesSubquery) );
			}
			
			if (filters.isUnesco())
				criteria.add(Restrictions.eq("isUnesco", true));
		}
		
        return new HashSet<>(criteria.list());
	}
	
	@Override
	public Place findById(int id) {
		Place place = getByKey(id);
		if(place!=null){
            Hibernate.initialize(place.getPlaceLinks());
            Hibernate.initialize(place.getPlaceImages());
		}	
		return place;
	}
	
	@Override
	public void deleteById(int id) {
        delete(getByKey(id));
	}
	
	@Override
	public int getImgCountForPlace(int id){
		Query query = getSession().createQuery("select count(*) from Image i where i.place.id=:id");
		query.setInteger("id", id);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Place> findAllMyPlaces() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    
	    Query query = getSession().createQuery("from Place p where p.createdBy.name = :name  ");
	    query.setString("name",name);
		
        return new HashSet<>(query.list());
    }
	
	public Country getCountryByIso2(String code){
		 return (Country) getSession().get(Country.class, code);
		
	}

} 
