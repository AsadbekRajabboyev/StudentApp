/******************************************************************************
 * Copyright (c)  2/13/24, 10:05 PM                                           *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TeacherDto {
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Surname is required")
	@Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
	private String surname;

	@NotBlank(message = "Job is required")
	private String job;

	@NotBlank(message = "Number is required")
	private String number;

	@NotBlank(message = "Address is required")
	private String address;


	@JsonFormat(pattern="yyyy-MM-dd")
	private Date work_time;

	private String sertificate_url;

	private int rating;

	@NotBlank(message = "Category is required")
	private String category;



	public TeacherDto() {
	}

	public TeacherDto(String name, String surname, String job, String number, String address, Date work_time, String sertificate_url, int rating, String category, byte[] image_data) {
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
