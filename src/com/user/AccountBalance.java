//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AccountBalance extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer account_no = (Integer) request.getSession().getAttribute(
				"account_no");
		if (account_no == null) {
			return mapping.findForward("session-expired");
		}
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		Double balance = user.fetchBalance(account_no);
		user.close();
		request.setAttribute("balance", balance);
		return mapping.findForward("success");
	}
}
