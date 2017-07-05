package com.dkmp.promotor;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;

import com.dkmp.auth.UserSessionBean;
import com.dkmp.common.exceptions.ValidateException;
import com.dkmp.common.utils.DroolsBean;
import com.dkmp.common.web.WebMessageAdder;
import com.dkmp.dao.PracaDao;
import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Praca;
import com.dkmp.model.Praca.Status;
import com.dkmp.model.Recenzent;

@Named
@Scope("view")
public class PromotorPracaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UserSessionBean userSessionBean;

	@Inject
	PracaDao pracaDao;

	@Inject
	RecenzentDao recenzentDao;
	
	@Inject
	DroolsBean droolsBean;

	private boolean isPracaValidPromotorPraca;
	private String pracaNotValidPromotorPracaMessage;
	private long idPraca;

	private Praca praca;

	private DualListModel<Recenzent> recenzenciPickListModel;

	private boolean pickListModified = false;

	@PostConstruct
	public void init() {
		System.out.println("PromotorPracaBean init()");

		try {
			idPraca = getIdPracyFromParam();
			validatePrace();
			isPracaValidPromotorPraca = true;
			praca = pracaDao.getPracaById(idPraca);
			zaladujPropozycjeRecenzentow();
		} catch (ValidateException e) {
			isPracaValidPromotorPraca = false;
			pracaNotValidPromotorPracaMessage = e.getMessage();
		}
	}
	

	public void onPickListTransfer() {
		pickListModified = true;
	}

	// TODO - unused przycisk do resetowania
	public void resetPickList() {
		pickListModified = false;
		zaladujPropozycjeRecenzentow();
	}

	private long getIdPracyFromParam() throws ValidateException {
		long id;
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			id = Long.valueOf(request.getParameter("id"));
		} catch (Exception e) {
			throw new ValidateException("Problem z podanym parametrem");
		}
		return id;
	}

	private void validatePrace() throws ValidateException {
		List<Praca> prace = pracaDao.getPromotorPrace(userSessionBean.getUserId());
		if (!prace.stream().anyMatch(praca -> praca.getIdPracy() == idPraca))
			throw new ValidateException("Brak uprawnieñ do pracy o podanym id");
		
	}

	public void przeslijPropozycjeRecenzentow() {
		System.out.println("przeslijPropozycjeRecenzentow()");
		praca.setListaProponowanychRecenzentow(getWybraniRecenzenciFromPickList());
		System.out.println("Size: " + praca.getListaProponowanychRecenzentow().size());
		try {
			droolsBean.validateRegulyBiznesowePracy(praca);
			pracaDao.przeslijPropozycjeRecenzentowPromotora(praca);
			praca.setStatus(Status.WAITING_FOR_STUDENT_REC_CONFIRM);
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
			droolsBean.validateRegulyBiznesowePracy(praca);
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

	private void zaladujPropozycjeRecenzentow() {
		List<Recenzent> wybraniRecenzenci = droolsBean.filtrujRecenzentowWgZasadBiznesowych(
				praca.getListaProponowanychRecenzentow());
		List<Recenzent> dostepniRecenzenci = filtrujRecenzentow(recenzentDao.getAllRecenzenci(), wybraniRecenzenci);
		recenzenciPickListModel = new DualListModel<Recenzent>(dostepniRecenzenci, wybraniRecenzenci);
	}

	public List<Recenzent> filtrujRecenzentow(List<Recenzent> wszyscyRecenzenci, List<Recenzent> wybraniRecenzenci) {
		List<Recenzent> filtredRecenzenci = droolsBean.filtrujRecenzentowWgZasadBiznesowych(wszyscyRecenzenci);
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

	public boolean isPracaValidPromotorPraca() {
		return isPracaValidPromotorPraca;
	}

	public String getPracaNotValidPromotorPracaMessage() {
		return pracaNotValidPromotorPracaMessage;
	}

}
