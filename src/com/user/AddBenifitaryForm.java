//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddBenifitaryForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer bAccountNo;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		Integer accountNo = (Integer) request.getSession().getAttribute(
				"account_no");
		System.out.println("Account No : " + bAccountNo + ", Name : " + name);
		ActionErrors errors = new ActionErrors();
		if (name == null || name.length() < 1) {
			errors.add("name", new ActionError("name.required"));
		} else if (name.length() > 30) {
			errors.add("name", new ActionError("name.overflow"));
		}
		if (bAccountNo == null || bAccountNo == 0) {
			errors.add("bAccountNo", new ActionError("bAccountNo.required"));
		} else if (bAccountNo.equals(accountNo)) {
			System.out.println("ERR : SELT ADD");
			errors.add("bAccountNo", new ActionError("bAccountNo.selfadd"));
		}
		return errors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public Integer getAccount_no() {
		return bAccountNo;
	}

	public void setAccount_no(Integer bAccountNo) {
		this.bAccountNo = bAccountNo;
	}

}
