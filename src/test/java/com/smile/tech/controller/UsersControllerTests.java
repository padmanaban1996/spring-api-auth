package com.smile.tech.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smile.tech.model.Users;
import com.smile.tech.service.AttendenceService;
import com.smile.tech.service.UsersService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTests {

	
	@InjectMocks
	UsersController controller;

	@Mock
	UsersService service1;
	
	@Mock
	AttendenceService service2;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllEmployeesTest() {
		  
		List<Users> list = new ArrayList<>();
		Users empOne = new Users("user","user12345");
		Users empTwo = new Users("manager","manager123");
		

		list.add(empOne);
		list.add(empTwo);
	

		when(service1.findAll()).thenReturn(list);
		List<Users> empList = controller.getAllUsers();
		assertEquals(2, empList.size());		
		verify(service1, times(1)).findAll();
		
		
		System.out.println("Test 1 working...");
	}
}
