//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewTransactions extends Action {
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
		user.fetchTransactions(account_no);
		user.close();
		request.setAttribute("id", user.ids);
		request.setAttribute("fromAccount", user.fromAccounts);
		request.setAttribute("toAccount", user.toAccounts);
		request.setAttribute("amount", user.amounts);
		request.setAttribute("timestamp", user.timestamps);
		return mapping.findForward("success");
	}
}
