package com.library.essay.faces.beans.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.Logger;

import com.library.essay.ejbs.EssayEJB;
import com.library.essay.persistence.entities.Essay;
import com.library.essay.services.EssayService;

//DI via JSF managed bean
//@Named("essayBean")
// This is the managed bean name that will be refered in xhtml pages. It acts as
// a controller.
@ManagedBean(name="essayBean")
@SessionScoped
public class EssayBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger("com.library.essay");

	// DI via Spring, but need to put
	// org.springframework.web.jsf.el.SpringBeanFacesELResolver in
	// faces-config.xml
	//@ManagedProperty(name = "essayService", value = "#{essayService}")
	//private EssayService essayService;
	
	@EJB
	private EssayEJB essayEJB;

	private Essay essay;

	public EssayBean() {
		essay = new Essay();
	}

	public List<Essay> getEssays() {
		//return essayService.getEssays();
		return essayEJB.findAllEssays();
	}

	public String showEssayAdmin() {

		FacesContext fc = FacesContext.getCurrentInstance();

		String essayIdStr = this.getEssayId(fc);
		long essayId = Long.parseLong(essayIdStr);

		this.essay = essayEJB.findEssayById(essayId); //essayService.getEssay(essayId);

		return "essay";
	}
	
	public String showEssay() {

		FacesContext fc = FacesContext.getCurrentInstance();

		String essayIdStr = this.getEssayId(fc);
		long essayId = Long.parseLong(essayIdStr);

		//this.essay = essayService.getEssay(essayId);

		this.essay=essayEJB.findEssayById(essayId);
		
		return "essay2";
	}

	// get value from "f:param"
	private String getEssayId(FacesContext fc) {

		// Use FacesContext to get the parameters passed from <f:param>
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		return params.get("essayId");

	}

	public String createNewEssay() {
		this.essay = new Essay();

		return "essay";
	}

	public String saveEssay() {

		//essay = essayService.saveOrUpdate(essay);
		
		essay=essayEJB.saveEssay(essay);

		return "essay";
	}

	public String deleteEssay() {

		//essayService.delete(essay);
		
		essayEJB.deleteEssay(essay);
		this.essay = new Essay();

		return "essay";
	}

//	public EssayService getEssayService() {
//		return essayService;
//	}
//
//	public void setEssayService(EssayService essayService) {
//		this.essayService = essayService;
//	}

	public Essay getEssay() {
		return essay;
	}

	public void setEssay(Essay essay) {
		this.essay = essay;
	}

}
