//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MoneyTransfer extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer account_no = (Integer) request.getSession().getAttribute(
				"account_no");
		if (account_no == null) {
			request.setAttribute("message", "Session expired");
			return mapping.findForward("failure");
		}
		Integer to_account = Integer.parseInt(request
				.getParameter("to_account"));
		Double amount = Double.parseDouble(request.getParameter("amount"));
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		boolean flag = user.isAccountExist(to_account);
		if (!flag) {
			request.setAttribute("message", "Account not Found");
			return mapping.findForward("failed");
		}
		flag = user.isMoneyEnough(account_no, amount);
		if (!flag) {
			request.setAttribute("message", "Not Enough Balance");
			return mapping.findForward("failed");
		}
		user.insertTransaction(account_no, to_account, amount);
		user.credit(to_account, amount);
		user.debit(account_no, amount);
		System.out.println("Transfer Success");
		user.close();
		if (flag) {
			return mapping.findForward("success");
		}
		return mapping.findForward("failure");
	}
}
