package com.fsd.projectmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.ParentTaskVO;
import com.fsd.projectmanager.bo.ProjectVO;
import com.fsd.projectmanager.bo.TaskVO;
import com.fsd.projectmanager.bo.UserVO;
import com.fsd.projectmanager.service.impl.ProjectServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {
	
	@InjectMocks
	ProjectController target;
	
	@Mock
	private ProjectServiceImpl projectService;
	
	private MockMvc mockMvc;

	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testHome() {
		String testhome = target.testHome();
		Assert.assertEquals(testhome, "Project Manager App - Test Task ");
	}
	
	@Test
	public void getTasks() {
		
		List<TaskVO> tasks =new ArrayList<TaskVO>();
				
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		tasks.add(taskVO);
		
		when(projectService.getAllTasks()).thenReturn(tasks);
		List<TaskVO> tasksList = target.getTasks();
	}
	
	@Test
	public void getTasksById() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		TaskVO task = target.getTasksById("1L");
		assertEquals("123456",task.getEmployeeId());
		assertEquals("12/08/2018",task.getEndDate());
		task.getParentTaskId();
		assertEquals("parenttask",task.getParentTaskName());
		assertEquals("14",task.getPriority());
		assertEquals("A",task.getStatus());
		task.getTaskId();
		assertEquals("taskname",task.getTaskName());
		
	}
	
	@Test
	public void saveTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		boolean status = target.saveTask(taskVO);
		
	}
	
	@Test
	public void updateTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		
		boolean status = target.updateTask(taskVO,"1L");
		
	}
	
	@Test
	public void deleteTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		boolean status = target.deleteTask("1L");
	}
	
	@Test
	public void getParentTasks() {
		
		List<ParentTaskVO> parentList=new ArrayList<ParentTaskVO>();
		ParentTaskVO parent=new ParentTaskVO();
		parent.setParentTaskId(1L);
		parent.setParentTaskName("parent1");
		parent.setProjectId(1L);
		
		
		when(projectService.getAllParentTasks()).thenReturn(parentList);
		List<ParentTaskVO> tasks =target.getParentTasks();
		
	}
	
	
	@Test
	public void getProjects() {
		List<ProjectVO> projectList =new ArrayList<ProjectVO>();
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		projectList.add(project);
		when(projectService.getAllProjects()).thenReturn(projectList);
		List<ProjectVO> tasks =target.getProjects();
		
	}
	
	@Test
	public void getProjectById() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		ProjectVO tasks =target.getProjectById("1");
		
		
		assertEquals("298960",project.getEmployeeId());
		assertEquals("12/08/2018",project.getEndDate());
		project.getNoOfTask();
		assertEquals("12",project.getPriority());
		project.getProjectId();
		
		assertEquals("Ebiz",project.getProjectName());
		assertEquals("12/08/2018",project.getStartDate());
		assertEquals("Active",project.getStatus());
		
	}
	
	
	@Test
	public void saveProject() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		boolean status=target.saveProject(project);
	}
	
	
	@Test
	public void updateProject() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		
		boolean status = target.updateProject(project,"1L");
		
	}
	
	@Test
	public void deleteprojects() {
		TaskVO taskVO=new TaskVO();
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		boolean status = target.deleteprojects("1L");
	}
	
	@Test
	public void getusers() {
		List<UserVO> userList =new ArrayList<UserVO>();
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userList.add(user);
		when(projectService.getAllUsers()).thenReturn(userList);
		List<UserVO> users =target.getusers();
		
	}
	
	@Test
	public void getUserById() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		UserVO userVO =target.getUserById("1");
		
		assertEquals("25469",user.getEmployeeId());
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("Active",user.getStatus());
	}
	
	@Test
	public void saveUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		boolean status=target.saveUser(user);
	}
	
	@Test
	public void updateUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		
		boolean status = target.updateUser(user,"1L");
		
	}
	
	@Test
	public void deleteUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		boolean status = target.deleteUser("1L");
	}
	
}
