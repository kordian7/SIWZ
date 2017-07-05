package com.dkmp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import com.dkmp.common.exceptions.ValidateException;
import com.dkmp.model.Praca;
import com.dkmp.model.Recenzent;

@Named
public class DroolsBean {

	@PostConstruct
	public void init() {
		// Przy pierwszym u¿yciu zabiera sporo czasu
		KieServices.Factory.get().getKieClasspathContainer().newKieSession("RecenzentKS").dispose();
	}
	
	public List<Recenzent> filtrujRecenzentowWgZasadBiznesowych(List<Recenzent> recenzenci) {
		List<Recenzent> filtredRecenzenci = new ArrayList<Recenzent>(recenzenci);
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("RecenzentKS");
		filtredRecenzenci.forEach(rec -> kSession.insert(rec));
		kSession.fireAllRules();
		kSession.dispose();
		return filtredRecenzenci.stream().filter(rec -> rec.isAvailable()).collect(Collectors.toList());
	}
	

	public void validateRegulyBiznesowePracy(Praca praca) throws ValidateException {
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("PracaKS");
		kSession.insert(praca);
		kSession.fireAllRules();
		kSession.dispose();
		if (!praca.isPracaOk())
			throw new ValidateException(praca.getPracaValidationError());
	}
}
