package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.AbstractDao;
import com.samsonan.bplaces.dao.PlaceDao;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;


@Repository("placeDao")
public class PlaceDaoImpl extends AbstractDao<Serializable, Place> implements PlaceDao {

	public void save(Place place) {
		if (findById(place.getId()) != null) {
			place.setUpdated(new Date());
			merge(place);
		} else {
			place.setUpdated(new Date());
//			place.setCreated(new Date()); done on DB level
			persist(place);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Place> findAll(PlaceFilters filters) {
		//select * from place p inner join place_type pt on p.id=pt.place_id where pt.type in (N1,N2,N3,C1,C2) 
		// [and p.unesco = true]

		//holy fuck! i've spent two hours trying to make it work. Just because place types are not entities, but elements!!!!
		//HQL will look like this! Note that it is not fully compliant with Criteria!!!!
		//Query query = getSession().createQuery("from Place p inner join p.placeTypes pt where pt in ('VILLAGE') ");
		
		Criteria criteria = createEntityCriteria();
		

		if (filters != null) {
			criteria.createAlias("placeTypes", "pt");
			
			String[] resList = (String[]) ArrayUtils.addAll( filters.getCulturalTypes(),  filters.getNaturalTypes());
			if (resList.length==0) resList = new String [] {""};//at least one element should be in the array
			criteria.add(Restrictions.in("pt.elements", resList));
			
			if (filters.isUnesco())
				criteria.add(Restrictions.eq("unesco", "true"));
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
		Place place =  getByKey(id);
        delete(place);
	}
	
	@Override
	public int getImgCountForPlace(int id){
		Query query = getSession().createQuery("select count(*) from Image i where i.place.id=:id");
		query.setInteger("id", id);
		return ((Long) query.uniqueResult()).intValue();
	}

} 
