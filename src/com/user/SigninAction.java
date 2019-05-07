package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.user.User;

public class SigninAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer account_no = null;
		String email = request.getParameter("email"), password = request
				.getParameter("password");
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		account_no = user.checkUser(email, password);
		user.close();
		System.out.println("Account no : " + account_no);
		if (account_no == null) {
			request.setAttribute("error", "Username or Password Invalid");
			return mapping.findForward("failure");
		}
		request.getSession().setAttribute("account_no", account_no);
		return mapping.findForward("success");
	}
}
