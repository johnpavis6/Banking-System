//$Id$
package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SignupAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean flag = false;
		String name = request.getParameter("name"), gender = request
				.getParameter("gender"), dob = request.getParameter("dob"), mobile_no = request
				.getParameter("mobile_no"), email = request
				.getParameter("email"), password = request
				.getParameter("password"), address = request
				.getParameter("address");
		DataSource ds = getDataSource(request, "bank");
		User user = new User(ds);
		flag = user.createUser(name, gender, dob, mobile_no, email, password,
				address);
		user.close();
		if (flag) {
			System.out.println("User created");
			return mapping.findForward("success");
		}
		request.setAttribute("error", "Email or Mobile No already Exists");
		return mapping.findForward("failure");
	}
}
