package com.learn.online.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.learn.online.custom.validation.annotations.FieldMatch;
import com.learn.online.custom.validation.annotations.ValidPassword;

@FieldMatch.List({
	@FieldMatch(first = "password", second="repassword", message = "{type.retyped.password.match}"),
	@FieldMatch(first = "email", second = "reemail" , message = "{type.retyped.email.match}")
})
public class StudentUpdateRequest {

	@NotBlank(message = "{firstname.mandatory}")
	private String firstName;

	@NotBlank(message = "{lastname.mandatory}")
	private String lastName;

	@NotBlank(message = "{email.mandatory}")
	@Email(message = "{email.is.not.valid}", regexp = ".+@.+\\.[a-z]+")
	private String email;

	@NotBlank(message = "{email.mandatory}")
	@Email(message = "{email.is.not.valid}", regexp = ".+@.+\\.[a-z]+")
	private String reemail;

	@NotEmpty
	@ValidPassword(message = "{password.requirement}")
	private String password;

	@NotEmpty
	@ValidPassword(message = "{password.requirement}")
	private String repassword;

	@Size(max = 10, min = 10, message = "{phone.number.length.invalid}")
	@Pattern(regexp = "[0-9]+", message = "{phone.number.value.invalid}")
	private String phone;
	
	private String country;
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

	public String getReemail() {
		return reemail;
	}

	public void setReemail(String reemail) {
		this.reemail = reemail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
				+ ", phone=" + phone + ", country=" + country + ", state=" + state + "]";
	}

}
