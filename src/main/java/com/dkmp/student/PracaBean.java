package com.dkmp.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;

import com.dkmp.auth.UserSessionBean;
import com.dkmp.common.exceptions.ValidateException;
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
			validateRegulyBiznesowePracy();
			praca.setStatus(Status.WAITING_FOR_PROMOTOR_REC_CONFIRM);
			throwInfoMessage("Przes³ano propozycjê recenzentow");
			// TODO - wywo³anie z serwisu
		} catch (ValidateException e) {
			throwErrorMessage(e.getMessage());
		}
	}
	

	public void zatwierdzPropozycjeRecenzentow() {
		System.out.println("zatwierdzPropozycjeRecenzentow()");
		praca.setListaProponowanychRecenzentow(getWybraniRecenzenciFromPickList());
		System.out.println("Size: " + praca.getListaProponowanychRecenzentow().size());
		try {
			validateRegulyBiznesowePracy();
			praca.setStatus(Status.REC_CONFIRMED);
			throwInfoMessage("Zatwierdzono recenzentow");
			// TODO - wywo³anie z serwisu
		} catch (ValidateException e) {
			throwErrorMessage(e.getMessage());
		}
	}
	


	private void validateRegulyBiznesowePracy() throws ValidateException {
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("PracaKS");
		kSession.insert(praca);
		kSession.fireAllRules();
		kSession.dispose();
		if (!praca.isPracaOk())
			throw new ValidateException(praca.getPracaValidationError());
	}

	private void throwErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", message));
	}

	private void throwInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
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
		List<Recenzent> wybraniRecenzenci = filtrujRecenzentowWgZasadBiznesowych(praca.getListaProponowanychRecenzentow());
		List<Recenzent> dostepniRecenzenci = filtrujRecenzentow(recenzentDao.getAllRecenzenci(), wybraniRecenzenci );
		recenzenciPickListModel = new DualListModel<Recenzent>(dostepniRecenzenci, wybraniRecenzenci);
	}
	
	private List<Recenzent> filtrujRecenzentow(List<Recenzent> wszyscyRecenzenci, List<Recenzent> wybraniRecenzenci) {
		List<Recenzent> filtredRecenzenci = filtrujRecenzentowWgZasadBiznesowych(wszyscyRecenzenci);
		filtredRecenzenci.remove(wybraniRecenzenci);
		return filtredRecenzenci;
	}

	private List<Recenzent> filtrujRecenzentowWgZasadBiznesowych(List<Recenzent> recenzenci) {
		List<Recenzent> filtredRecenzenci = new ArrayList<Recenzent>(recenzenci);
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("RecenzentKS");
		filtredRecenzenci.forEach(rec -> kSession.insert(rec));
		kSession.fireAllRules();
		kSession.dispose();
		return filtredRecenzenci.stream().filter(rec -> rec.isAvailable()).collect(Collectors.toList());
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
