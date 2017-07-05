package com.dkmp.student;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;

import com.dkmp.auth.UserSessionBean;
import com.dkmp.common.exceptions.ValidateException;
import com.dkmp.common.utils.DroolsUtils;
import com.dkmp.common.web.WebMessageAdder;
import com.dkmp.dao.PracaDao;
import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Praca;
import com.dkmp.model.Praca.Status;
import com.dkmp.model.Recenzent;

@Named
@Scope("view")
public class PracaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	UserSessionBean userSessionBean;
	
	@Inject
	PracaDao pracaDao;
	
	@Inject
	RecenzentDao recenzentDao;
	
	private Praca praca;
	
	private DualListModel<Recenzent> recenzenciPickListModel;
	
	private boolean pickListModified = false;
	
	@PostConstruct
	public void init() {
		praca = pracaDao.getStudentPraca(userSessionBean.getUserId());
		System.out.println("PracaBean init()");
		zaladujPropozycjeRecenzentow();
	}
	
	public void przeslijPropozycjeRecenzentow() {
		System.out.println("przeslijPropozycjeRecenzentow()");
		praca.setListaProponowanychRecenzentow(getWybraniRecenzenciFromPickList());
		System.out.println("Size: " + praca.getListaProponowanychRecenzentow().size());
		try {
			DroolsUtils.validateRegulyBiznesowePracy(praca);
			pracaDao.przeslijPropozycjeRecenzentowStudenta(praca);
			praca.setStatus(Status.WAITING_FOR_PROMOTOR_REC_CONFIRM);
			WebMessageAdder.addInfoMessage("Przes³ano propozycjê recenzentow");
		} catch (ValidateException e) {
			WebMessageAdder.addErrorMessage(e.getMessage());
		}
	}
	

	public void zatwierdzPropozycjeRecenzentow() {
		System.out.println("zatwierdzPropozycjeRecenzentow()");
		praca.setListaProponowanychRecenzentow(getWybraniRecenzenciFromPickList());
		System.out.println("Size: " + praca.getListaProponowanychRecenzentow().size());
		try {
			DroolsUtils.validateRegulyBiznesowePracy(praca);
			pracaDao.zatwierdzPropozycjeRecenzentow(praca);
			praca.setStatus(Status.REC_CONFIRMED);
			WebMessageAdder.addInfoMessage("Zatwierdzono recenzentow");
		} catch (ValidateException e) {
			WebMessageAdder.addErrorMessage(e.getMessage());
		}
	}
	
	private List<Recenzent> getWybraniRecenzenciFromPickList() {
		return recenzenciPickListModel.getTarget();
	}
	
	public void onPickListTransfer() {
		System.out.println("onPickListTransfer()");
		pickListModified = true;
	}
	
	public void resetPickList() {
		System.out.println("resetPickList()");
		pickListModified = false;
		zaladujPropozycjeRecenzentow();
	}
	
	private void zaladujPropozycjeRecenzentow() {
		List<Recenzent> wybraniRecenzenci = DroolsUtils.filtrujRecenzentowWgZasadBiznesowych(praca.getListaProponowanychRecenzentow());
		List<Recenzent> dostepniRecenzenci = filtrujRecenzentow(recenzentDao.getAllRecenzenci(), wybraniRecenzenci );
		recenzenciPickListModel = new DualListModel<Recenzent>(dostepniRecenzenci, wybraniRecenzenci);
	}
	
	private List<Recenzent> filtrujRecenzentow(List<Recenzent> wszyscyRecenzenci, List<Recenzent> wybraniRecenzenci) {
		List<Recenzent> filtredRecenzenci = DroolsUtils.filtrujRecenzentowWgZasadBiznesowych(wszyscyRecenzenci);
		filtredRecenzenci.remove(wybraniRecenzenci);
		return filtredRecenzenci;
	}

	public Praca getPraca() {
		return praca;
	}

	public void setPraca(Praca praca) {
		this.praca = praca;
	}

	public DualListModel<Recenzent> getRecenzenciPickListModel() {
		return recenzenciPickListModel;
	}

	public void setRecenzenciPickListModel(DualListModel<Recenzent> recenzenciPickListModel) {
		this.recenzenciPickListModel = recenzenciPickListModel;
	}

	public boolean isPickListModified() {
		return pickListModified;
	}
	
}
