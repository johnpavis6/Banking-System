package com.user;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SigninActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		Pattern emailPattern = Pattern
				.compile("[a-zA-Z][a-zA-Z0-9_.]*@[a-z]+\\.[a-zA-Z]+");
		if (email == null || email.length() < 1) {
			errors.add("email", new ActionError("email.required"));
		} else if (email.length() > 30) {
			errors.add("email", new ActionError("email.overflow"));
		} else if (!emailPattern.matcher(email).matches()) {
			System.out.print("here");
			errors.add("email", new ActionError("email.invalid"));
		}
		if (password == null) {
			errors.add("password", new ActionError("password.required"));
		} else if (password.length() < 6) {
			errors.add("password", new ActionError("password.notenough"));
		} else if (password.length() > 15) {
			errors.add("password", new ActionError("password.overflow"));
		}
		return errors;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

}
