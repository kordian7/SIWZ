package com.dkmp.common.utils;

import java.util.PropertyResourceBundle;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class BundleProducer {
	
	public PropertyResourceBundle getBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{properties}", PropertyResourceBundle.class);
	}
}
