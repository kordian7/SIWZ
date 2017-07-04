package com.dkmp.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;

import com.dkmp.auth.UserSessionBean;
import com.dkmp.dao.PracaDao;
import com.dkmp.dao.RecenzentDao;
import com.dkmp.model.Praca;
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
	
	private String console;
	
	private DualListModel<Recenzent> recenzenciPickListModel;
	
	@PostConstruct
	public void init() {
		praca = pracaDao.getStudentPraca(userSessionBean.getUserId());
		System.out.println("PracaBean init()");
		zaladujRecenzentow();
	}
	
	private void zaladujRecenzentow() {
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
	
	public void przeslijPropozycjeRecenzentow() {
		System.out.println("przeslijPropozycjeRecenzentow()");
		recenzenciPickListModel.getTarget().forEach(rec -> System.out.println(rec.getFormattedInfo()));
	}
	
	public void zatwierdzPropozycjeRecenzentow() {
		System.out.println("zatwierdzPropozycjeRecenzentow()");
		recenzenciPickListModel.getTarget().forEach(rec -> System.out.println(rec.getFormattedInfo()));
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

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
	
	
	
}
