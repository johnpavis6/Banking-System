//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddBenifitary extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer accountNo = (Integer) request.getSession().getAttribute(
				"account_no");
		if (accountNo == null) {
			request.setAttribute("message", "Session Expired");
			return mapping.findForward("failure");
		}
		String name = request.getParameter("name");
		Integer bAccountNo = Integer.parseInt(request
				.getParameter("account_no"));
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		boolean flag = user.isAccountExist(bAccountNo);
		if (!flag) {
			request.setAttribute("message", "Account not Found");
			return mapping.findForward("failed");
		}
		flag = user.isBenifitaryExist(accountNo, bAccountNo);
		if (!flag) {
			request.setAttribute("message", "Benifitary Exists");
			return mapping.findForward("failed");
		}
		user.addBenifitary(accountNo, name, bAccountNo);
		user.close();
		return mapping.findForward("success");
	}
}
