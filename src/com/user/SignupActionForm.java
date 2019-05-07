//$Id$
package com.user;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SignupActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String name;
	private String gender;
	private String dob;
	private String mobile_no;
	private String email;
	private String password;
	private String address;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (name == null || name.length() < 1) {
			errors.add("name", new ActionError("name.required"));
		} else if (name.length() > 30) {
			errors.add("name", new ActionError("name.overflow"));
		}
		if (gender == null || gender.length() < 1) {
			errors.add("gender", new ActionError("gender.required"));
		} else if (!gender.equals("male") && !gender.equals("female")
				&& !gender.equals("other")) {
			errors.add("gender", new ActionError("gender.invalid"));
		}
		Pattern dobPattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		if (dob == null || dob.length() < 1) {
			errors.add("dob", new ActionError("dob.required"));
		} else if (!dobPattern.matcher(dob).matches()) {
			errors.add("dob", new ActionError("dob.invalid"));
		}
		if (mobile_no == null || mobile_no.length() < 1) {
			errors.add("mobile_no",
					new ActionError("mobile_no.required"));
		} else if (mobile_no.length() != 10) {
			errors.add("mobile_no", new ActionError("mobile_no.invalid"));
		}
		Pattern emailPattern = Pattern
				.compile("[a-zA-Z][a-zA-Z0-9_.]*@[a-z]+\\.[a-zA-Z]+");
		if (email == null || email.length() < 1) {
			errors.add("email", new ActionError("email.required"));
		} else if (email.length() > 30) {
			errors.add("email", new ActionError("email.overflow"));
		} else if (!emailPattern.matcher(email).matches()) {
			errors.add("email", new ActionError("email.invalid"));
		}
		if (password == null || password.length() < 1) {
			errors.add("password", new ActionError("password.required"));
		} else if (password.length() < 6) {
			errors.add("password", new ActionError("password.notenough"));
		} else if (password.length() > 15) {
			errors.add("password", new ActionError("password.overflow"));
		}
		if (address == null || address.length() < 1) {
			errors.add("address", new ActionError("address.required"));
		} else if (address.length() > 100) {
			errors.add("address", new ActionError("address.overflow"));
		}
		return errors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender.trim();
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob.trim();
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no.trim();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}
}
