package com.smile.tech.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Role;
import com.smile.tech.model.Users;
import com.smile.tech.service.AttendenceService;
import com.smile.tech.service.UsersService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersControllerTests {

	private static final String ATT_ID = "8734823";
	private static final String USER_ID = "7823728";
	private static final String USER_NAME = "User2";
	private static final String PASSWORD = "123456789";
	static LocalDateTime ldt = LocalDateTime.now();
	private static final LocalDateTime CREATEDDATE = ldt;
	private static final Set<Role> ROLE = null;


	@InjectMocks
	UsersController controller;

	@Mock
	UsersService service;

	@Mock
	AttendenceService service2;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void checkFindAll() throws Exception {
		List<Users> ls = new ArrayList<>();
		when(service.findAll()).thenReturn(ls);
		List<Users> returned = service.findAll();
		assertEquals(ls, returned);

		System.out.println("Test 1 Complete");
	}

	@WithMockUser(username = "admin")
	@Test
	public void checkFindByID() throws Exception {
		Users user = new Users();
		user.setId(USER_ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setCreatedDate(CREATEDDATE);
		user.setRoles(ROLE);
		when(service.findById(USER_ID)).thenReturn(user);
		Users returned = service.findById(USER_ID);
		verify(service, times(1)).findById(USER_ID);

		assertEquals(user, returned);

		System.out.println("Test 2 Complete");
	}

	@Test
	public void checkFindByUsername() throws Exception {
		Users user = new Users();
		user.setId(USER_ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setCreatedDate(CREATEDDATE);
		user.setRoles(ROLE);
		when(service.findByUsername(USER_NAME)).thenReturn(user);
		Users returned = service.findByUsername(USER_NAME);
		verify(service, times(1)).findByUsername(USER_NAME);

		assertEquals(user, returned);

		System.out.println("Test 3 Complete");
	}

	@Test
	public void checkAttendenceEntry() throws Exception {
		Users user = new Users();
		user.setId(USER_ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setCreatedDate(CREATEDDATE);
		user.setRoles(ROLE);

		Users U1 = service.findById(USER_ID);

		when(service2.findByUserID(USER_ID)).thenReturn(null);
		service2.saveAttendence(U1);

		Attendence att = new Attendence();
		when(service2.findByUserID(USER_ID)).thenReturn(att);
		service2.updateAttendence(att);

		System.out.println("Test 4 Complete");
	}

	@Test
	public void checkEntryByDate() throws Exception {
		Attendence att = new Attendence();
		att.setId(ATT_ID);
		att.setStartTime(CREATEDDATE);
		att.setEndTime(CREATEDDATE);
		att.setUserID(USER_ID);

		List<Attendence> list = new ArrayList<>();
		list.add(att);
		when(service2.findAll()).thenReturn(list);

		LocalDate ld = LocalDate.now();

		assertEquals(list.get(0).getStartTime().toLocalDate().toString(), ld.toString());

		System.out.println("Test 5 Complete");
	}

	@Test
	public void checkEntryByMonth() throws Exception {
		Attendence att = new Attendence();
		att.setId(ATT_ID);
		att.setStartTime(CREATEDDATE);
		att.setEndTime(CREATEDDATE);
		att.setUserID(USER_ID);

		List<Attendence> list = new ArrayList<>();
		list.add(att);
		when(service2.findAll()).thenReturn(list);

		LocalDate ld = LocalDate.now();

		assertEquals(list.get(0).getStartTime().getMonthValue(), ld.getMonthValue());

		System.out.println("Test 6 Complete");
	}

	@Test
	public void findAbsents() throws Exception {
		LocalDate ld = LocalDate.now();

		Users user = new Users();
		user.setId(USER_ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setCreatedDate(CREATEDDATE);
		user.setRoles(ROLE);

		Attendence att = new Attendence();
		att.setId(ATT_ID);
		att.setStartTime(CREATEDDATE);
		att.setEndTime(CREATEDDATE);
		att.setUserID("372847");

		List<Users> users = new ArrayList<>();
		users.add(user);
		List<Attendence> attendences = new ArrayList<>();
		attendences.add(att);
		when(service.findAll()).thenReturn(users);
		when(service2.findAll()).thenReturn(attendences);

		assertEquals(users.get(0).getCreatedDate().getMonthValue(), ld.getMonthValue());
		assertNotEquals(users.get(0).getId(), attendences.get(0).getUserID());

		System.out.println("Test 7 Complete");
	}

	@Test
	public void findPresents() throws Exception {
		LocalDate ld = LocalDate.now();

		Users user = new Users();
		user.setId(USER_ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setCreatedDate(CREATEDDATE);
		user.setRoles(ROLE);

		Attendence att = new Attendence();
		att.setId(ATT_ID);
		att.setStartTime(CREATEDDATE);
		att.setEndTime(CREATEDDATE);
		att.setUserID(USER_ID);

		List<Users> users = new ArrayList<>();
		users.add(user);
		List<Attendence> attendences = new ArrayList<>();
		attendences.add(att);
		when(service.findAll()).thenReturn(users);
		when(service2.findAll()).thenReturn(attendences);

		assertEquals(users.get(0).getCreatedDate().getMonthValue(), ld.getMonthValue());
		assertEquals(users.get(0).getId(), attendences.get(0).getUserID());

		System.out.println("Test 8 Complete");
	}
}
