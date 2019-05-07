//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MoneyTransferForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private Integer to_account;
	private Double amount;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		Integer from_account = (Integer) request.getSession().getAttribute(
				"account_no");
		ActionErrors errors = new ActionErrors();
		System.out.println("From : " + from_account + ", To : " + to_account);
		if (to_account == null || to_account == 0) {
			errors.add("to_account", new ActionError("to_account.required"));
		} else if (from_account.equals(to_account)) {
			errors.add("to_account", new ActionError("to_account.selftransfer"));
		}
		if (amount == null) {
			errors.add("amount", new ActionError("amount.required"));
		} else if (amount < 1) {
			errors.add("amount", new ActionError("amount.invalid"));
		}
		return errors;
	}

	public Integer getTo_account() {
		return to_account;
	}

	public void setTo_account(Integer to_account) {
		this.to_account = to_account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
