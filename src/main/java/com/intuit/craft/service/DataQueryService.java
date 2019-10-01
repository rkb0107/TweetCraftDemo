package com.intuit.craft.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craft.exception.InternalServerException;

/**
 * This is the utility class is used to run the native query with the support of Hibernate with JPA
 * And helps to fetch the Query for the limited or pagination of records.
 */
@Service
public class DataQueryService {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unchecked")
	public List<Object> getQueryResults(String query, int offset, int limit) throws Exception {
		EntityManager session = entityManagerFactory.createEntityManager();

		List<Object> results = null;
		try {
			Query sqlQuery = session.createNativeQuery(query);
			
			// Pagination start from offset 0 and max 10
			sqlQuery.setFirstResult(offset);
			sqlQuery.setMaxResults(limit);

			results = (List<Object>) sqlQuery.getResultList();
		} catch (Exception e) {
			throw new InternalServerException("Error throw during Get query results. " + e.getCause());
		} finally {
			if (session.isOpen())
				session.close();
		}

		return results;
	}

}
