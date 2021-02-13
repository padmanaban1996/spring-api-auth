package com.smile.tech.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.smile.tech.model.Users;

@Document
public class Attendence implements Comparable<Attendence> {

	@Id
	private String id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String userID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getYear() {
		return this.startTime.getYear();
	}

	public int getMonth() {
		return this.startTime.getMonthValue();
	}

	public int getDayOfMonth() {
		return this.startTime.getDayOfMonth();
	}


	public String getDate() {
		return java.sql.Date.valueOf(startTime.toLocalDate()).toString();
	}
	
	public DayOfWeek getDayofWeek() {
		return this.startTime.getDayOfWeek();
	}
	
	@Override
	public int compareTo(Attendence other) {
		return this.startTime.compareTo(other.startTime);
	}

	public String toString() {
		return this.startTime.toString();
	}
}
