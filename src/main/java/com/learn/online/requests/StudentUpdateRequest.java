package com.learn.online.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class StudentUpdateRequest {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@Email(message = "{email.is.not.valid}")
	@NotEmpty(message = "{email.mandatory}")
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String city;

	@NotEmpty
	private String country;
	@NotEmpty
	private String state;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "StudentUpdateRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", city=" + city + ", country=" + country + ", state=" + state + "]";
	}

}
