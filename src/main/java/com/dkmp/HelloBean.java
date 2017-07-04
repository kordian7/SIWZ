package com.dkmp;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import com.dkmp.dao.TestDao;
import com.dkmp.model.Recenzent;

@Component
@ManagedBean
public class HelloBean {

	@Inject
	TestDao testDao;

	public String getMessage() {
		Recenzent rec1 = new Recenzent(1L, "Adam", "Kowalski", 3);
		Recenzent rec2 = new Recenzent(2L, "Bartosz", "Kowalski", 6);
		Recenzent rec3 = new Recenzent(3L, "Cezary", "Kowalski", 5);
		Recenzent rec4 = new Recenzent(4L, "Damian", "Kowalski", 7);

		List<Recenzent> recs = new ArrayList<Recenzent>();
		recs.add(rec1);
		recs.add(rec2);
		recs.add(rec3);
		recs.add(rec4);
		KieSession kSession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("RecenzentKS");
		System.out.println("kSession: " + kSession);
		recs.forEach(rec -> kSession.insert(rec));
		kSession.fireAllRules();
		kSession.dispose();
		System.out.println("TEST REC");
		recs.stream().filter(rec -> rec.isAvailable()).forEach(rec -> System.out.println(rec));
		return "Hello World" + testDao.getTest();
	}
}
