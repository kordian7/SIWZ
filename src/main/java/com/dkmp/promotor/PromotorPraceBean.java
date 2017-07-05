package com.dkmp.promotor;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.dkmp.auth.UserSessionBean;
import com.dkmp.dao.PracaDao;
import com.dkmp.model.Praca;

@Named
@Scope("view")
public class PromotorPraceBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UserSessionBean userSessionBean;
	
	@Inject
	PracaDao pracaDao;
	
	private List<Praca> prace;
	
	@PostConstruct
	public void init() {
		prace = pracaDao.getPromotorPrace(userSessionBean.getUserId());
	}

	// getters and setters
	
	public List<Praca> getPrace() {
		return prace;
	}

	public void setPrace(List<Praca> prace) {
		this.prace = prace;
	}
	
	
}
