package com.library.essay.services;

import java.util.List;

import javax.ejb.EJB;

import com.library.essay.ejbs.EssayEJB;
import com.library.essay.persistence.entities.Essay;

public class EssayServiceImp implements EssayService {

	//@EJB
	private EssayEJB essayEJB;

	@Override
	public Essay getEssay(long id) {

		return essayEJB.findEssayById(id);
	}

	@Override
	public List<Essay> getEssays() {

		return essayEJB.findAllEssays();
	}

	@Override
	public Essay saveOrUpdate(Essay essay) {

		return essayEJB.saveEssay(essay);
	}

	@Override
	public void delete(Essay essay) {
		essayEJB.deleteEssay(essay);
	}

}
