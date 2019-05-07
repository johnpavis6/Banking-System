//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MyBenifitaryAccounts extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer account_no = (Integer) request.getSession().getAttribute(
				"account_no");
		if (account_no == null) {
			request.setAttribute("message", "Session Expired");
			return mapping.findForward("failure");
		}
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		user.fetchBenifitaryAccounts(account_no);
		user.close();
		request.setAttribute("bNames", user.bNames);
		request.setAttribute("bAccountNos", user.bAccountNos);
		return mapping.findForward("success");
	}
}
