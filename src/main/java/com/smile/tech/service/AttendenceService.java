package com.smile.tech.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Users;
import com.smile.tech.payload.response.MessageResponse;
import com.smile.tech.repository.AttendenceRepository;

@Service
public class AttendenceService {

	@Autowired
	AttendenceRepository repository;

	public Attendence saveAttendence(Users user) {

		LocalDateTime date = LocalDateTime.now();

		Attendence attendence = new Attendence();
		attendence.setStartTime(date);
		attendence.setUserID(user.getId());

		return repository.save(attendence);
	}

	public List<Attendence> findAll() {
		return repository.findAll();
	}

	public Attendence findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourecNotFoundException());
	}

	public Attendence updateAttendence(Attendence attendence) {
		LocalDateTime date = LocalDateTime.now();
		attendence.setEndTime(date);
		return repository.save(attendence);
	}

	public Attendence findByUserID(String id) {
		return repository.findByUserID(id);
	}

	public ResponseEntity<?> attendenceRecord(Users user, List<Attendence> attendence) {

		HttpHeaders headers = new HttpHeaders();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate today = localDateTime.toLocalDate();
		List<Attendence> ls = new ArrayList<>();
		if (attendence.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(saveAttendence(user));
		}
		for (int i = 0; i < attendence.size(); i++) {
			if (user.getId().equals(attendence.get(i).getUserID())) {
				ls.add(attendence.get(i));
			}
		}
		if (ls.size() > 0) {
			boolean check = false;
			for (int j = 0; j < ls.size(); j++) {
				if (ls.get(j).getStartTime().toLocalDate().equals(today)) {
					check = true;
					return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers)
							.body(updateAttendence(ls.get(j)));
				}
			}
			if (!check) {
				return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(saveAttendence(user));
			}
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(saveAttendence(user));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Error: something went wrong..."));
	}

	public List<Attendence> findListByMonth(List<Attendence> ls, int month) {
		List<Attendence> list = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getStartTime().getMonthValue() == month) {
				list.add(ls.get(i));
			}
		}
		return list;
	}

	public List<Attendence> findListByDate(List<Attendence> ls, String date) {
		List<Attendence> list = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getStartTime().toLocalDate().toString().equals(date)) {
				list.add(ls.get(i));
			}
		}
		return list;
	}

	public List<Attendence> findAbsentListByMonth(List<Users> users, int month) {
		List<Attendence> ls = findAll();
		List<Attendence> list = new ArrayList<>();

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getStartTime().getMonthValue() == month) {
				list.add(ls.get(i));
			}
		}
		List<Attendence> absent = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			boolean checks = false;
			for (int j = 0; j < users.size(); j++) {
				if (list.get(i).getUserID().equals(users.get(j).getId())) {
					checks = true;
				}
			}
			if (checks == false) {
				absent.add(list.get(i));
			}
		}
		return absent;
	}

	public List<Attendence> findPresentListByMonth(List<Users> users, int month) {
		List<Attendence> lst = findAll();
		List<Attendence> list1 = new ArrayList<>();
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getStartTime().getMonthValue() == month) {
				list1.add(lst.get(i));
			}
		}
		List<Attendence> present = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			boolean checks = false;
			for (int j = 0; j < users.size(); j++) {
				if (list1.get(i).getUserID().equals(users.get(j).getId())) {
					checks = true;
				}
			}
			if (checks == true) {
				present.add(list1.get(i));
			}
		}
		return present;
	}

}
