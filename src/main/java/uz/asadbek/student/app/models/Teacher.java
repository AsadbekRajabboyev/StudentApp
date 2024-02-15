/******************************************************************************
 * Copyright (c)  2/13/24, 8:43 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "teachers")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name ="surname" )
	private String surname;
	@Column(name ="job" )
	private String job;
	@Column(name = "number")
	private String number;
	@Column(name = "address")
	private String address;
	@Column(name = "work_time")
	private Date work_time;
	@Column(name = "sertificate_url")
	private String sertificate_url;
	@Column(name = "rating")
	private int rating;
	@Column(name = "category")
	private String category;

	public Teacher() {
	}

	public Teacher(int id, String name, String surname, String job, String number, String address, Date work_time, String sertificate_url, int rating, String category) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.number = number;
		this.address = address;
		this.work_time = work_time;
		this.sertificate_url = sertificate_url;
		this.rating = rating;
		this.category = category;
	}

	public Teacher(String name, String surname, String job, String number, String address, Date work_time, String sertificate_url, int rating, String category) {
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.number = number;
		this.address = address;
		this.work_time = work_time;
		this.sertificate_url = sertificate_url;
		this.rating = rating;
		this.category = category;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getWork_time() {
		return work_time;
	}

	public void setWork_time(Date work_time) {
		this.work_time = work_time;
	}

	public String getSertificate_url() {
		return sertificate_url;
	}

	public void setSertificate_url(String sertificate_url) {
		this.sertificate_url = sertificate_url;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
