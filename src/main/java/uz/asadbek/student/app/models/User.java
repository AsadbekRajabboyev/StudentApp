/******************************************************************************
 * Copyright (c)  2/15/24, 9:04 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@Column(name = "phone_number")
	private int phone_number;
	@Column(name = "created_at")
	private LocalDateTime created_at;


	public User() {
	}

	public User(String name, String surname, String username, String password, String role, int phone_number, LocalDateTime created_at) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone_number = phone_number;
		this.created_at = created_at;
	}

	public User(int id, String name, String surname, String username, String password, String role, int phone_number, LocalDateTime created_at) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone_number = phone_number;
		this.created_at = created_at;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getRole() {
		return role;
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
