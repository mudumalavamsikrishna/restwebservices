/*package com.example.demo.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoService {

	@PersistenceContext
	EntityManager entityManager;

	public List<User> finaAll() {
		TypedQuery<User> namedQuery = entityManager.createNamedQuery("find_all-user_table", User.class);
		return namedQuery.getResultList();
	}

	public User insert(User user) {
		return entityManager.merge(user);
	}

	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

}
*/