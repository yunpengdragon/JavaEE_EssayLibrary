package com.library.essay.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.library.essay.persistence.entities.Essay;

@Stateless
public class EssayEJB {

	@Inject
	private EntityManager em;

	public Essay saveEssay(Essay essay) {

		Essay savedEssay = null;

		if (essay.getId() == null || essay.getId() <= 0) {
			em.persist(essay);
			savedEssay = essay;
		} else {
			savedEssay = em.merge(essay);
		}
		return savedEssay;
	}

	public List<Essay> findAllEssays() {
		return em.createNamedQuery("findAllEssays", Essay.class)
				.getResultList();
	}

	public Essay findEssayById(Long id) {
		return em.find(Essay.class, id);
	}

	public void deleteEssay(Essay essay) {

		Essay essayDeleted = findEssayById(essay.getId());
		em.remove(essayDeleted);
	}
}
