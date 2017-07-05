package com.dkmp.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import com.dkmp.common.exceptions.ValidateException;
import com.dkmp.model.Praca;
import com.dkmp.model.Recenzent;

public class DroolsUtils {

	public static List<Recenzent> filtrujRecenzentowWgZasadBiznesowych(List<Recenzent> recenzenci) {
		List<Recenzent> filtredRecenzenci = new ArrayList<Recenzent>(recenzenci);
		System.out.println(new Date());
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("RecenzentKS");
		System.out.println(new Date());
		filtredRecenzenci.forEach(rec -> kSession.insert(rec));
		kSession.fireAllRules();
		kSession.dispose();
		return filtredRecenzenci.stream().filter(rec -> rec.isAvailable()).collect(Collectors.toList());
	}
	

	public static void validateRegulyBiznesowePracy(Praca praca) throws ValidateException {
		System.out.println(new Date());
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("PracaKS");
		System.out.println(new Date());
		kSession.insert(praca);
		kSession.fireAllRules();
		kSession.dispose();
		if (!praca.isPracaOk())
			throw new ValidateException(praca.getPracaValidationError());
	}
}
