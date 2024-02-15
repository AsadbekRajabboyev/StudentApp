/******************************************************************************
 * Copyright (c)  2/15/24, 9:13 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.dto;

public class UserRegisterDto{

	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private int phone_number;

	public UserRegisterDto(String name, String surname, String username, String password, int phone_number) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
	}

	public UserRegisterDto(int id, String name, String surname, String username, String password, int phone_number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
	}

	public UserRegisterDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
}
