package com.fsd.projectmanager.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.ParentTaskVO;
import com.fsd.projectmanager.bo.ProjectVO;
import com.fsd.projectmanager.bo.TaskVO;
import com.fsd.projectmanager.bo.UserVO;
import com.fsd.projectmanager.entity.ParentTask;
import com.fsd.projectmanager.entity.Project;
import com.fsd.projectmanager.entity.Task;
import com.fsd.projectmanager.entity.User;
import com.fsd.projectmanager.repository.ParentManagerRepository;
import com.fsd.projectmanager.repository.ProjectManagerRepository;
import com.fsd.projectmanager.repository.TaskManagerRepository;
import com.fsd.projectmanager.repository.UserManagerRepository;

public class ProjectServiceImplTest {
	
	@InjectMocks
	private ProjectServiceImpl target;
	
	@Mock
	private TaskManagerRepository taskManagerRepository;
	
	@Mock
	private ProjectManagerRepository projectManagerRepository;
	
	@Mock
	private ParentManagerRepository parentManagerRepository;
	
	@Mock
	private UserManagerRepository userManagerRepository;
	
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
	public void getAllTasks() {
		List<Task> tasks =new ArrayList<Task>();
		
		Task task=new Task();
		task.setEndDate("12/08/2018");
		ParentTask pt=new ParentTask();
		pt.setParentTaskId(1L);
		pt.setParentTaskName("parentTaskName");
		pt.setProjectId(1L);
		task.setParentTask(pt);
		task.setPriority("12");
		Project pd=new Project();
		pd.setEndDate("12/08/2018");
		pd.setManagerId("managerId");
		pd.setPriority("priority");
		pd.setProjectId(1L);
		pd.setProjectName("projectName");
		pd.setStartDate("12/08/2018");
		pd.setStatus("A");
		task.setProjectDetails(pd);
		task.setStartDate("12/08/2018");
		task.setStatus("A");
		task.setTaskId(1L);
		task.setTaskName("taskName");
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		task.setUserDetails(user);
		
		tasks.add(task);
		
		when(taskManagerRepository.findAll()).thenReturn(tasks);
		
		List<TaskVO> tasksList = target.getAllTasks();
		
	}
	
	@Test
	public void saveTask() {
		
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		//taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskName("taskname");
		
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		//when(parentManagerRepository.findById(Mockito.anyLong()).get()).thenReturn(parent);
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		Optional<Project> a = Optional.ofNullable(prjct);


		when(projectManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		User usr=new User();
		usr.setEmployeeId("123456");
		usr.setFirstName("firstName");
		usr.setLastName("lastName");
		usr.setStatus("status");
		Optional<User> u = Optional.ofNullable(usr);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(u);
		target.saveTask(taskVO);
		
	}
	

	@Test
	public void getTask() {
		
		Task task=new Task();
		task.setEndDate("12/08/2018");
		ParentTask pt=new ParentTask();
		pt.setParentTaskId(1L);
		pt.setParentTaskName("parentTaskName");
		pt.setProjectId(1L);
		task.setParentTask(pt);
		task.setPriority("12");
		Project pd=new Project();
		pd.setEndDate("12/08/2018");
		pd.setManagerId("managerId");
		pd.setPriority("priority");
		pd.setProjectId(1L);
		pd.setProjectName("projectName");
		pd.setStartDate("12/08/2018");
		pd.setStatus("A");
		task.setProjectDetails(pd);
		task.setStartDate("12/08/2018");
		task.setStatus("A");
		task.setTaskId(1L);
		task.setTaskName("taskName");
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		task.setUserDetails(user);
		
		Optional<Task> a = Optional.ofNullable(task);
		
		when(taskManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		TaskVO taskVO=target.getTask("1");
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
		taskVO.setTaskName("taskname");
		
		
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		Optional<ParentTask> p = Optional.ofNullable(parent);
		when(parentManagerRepository.findById(Mockito.anyLong())).thenReturn(p);
		
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		Optional<Project> a = Optional.ofNullable(prjct);
		when(projectManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		
		
		User usr=new User();
		usr.setEmployeeId("123456");
		usr.setFirstName("firstName");
		usr.setLastName("lastName");
		usr.setStatus("status");
		Optional<User> u = Optional.ofNullable(usr);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(u);
		
		target.updateTask(taskVO);
		
	}
	
	@Test
	public void getAllParentTasks() {
		List<ParentTask> parentList=new ArrayList<ParentTask>();
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		parent.setParentTaskId(1L);
		parentList.add(parent);
		
		when(parentManagerRepository.findAll()).thenReturn(parentList);
		 List<ParentTaskVO> pt=target.getAllParentTasks();
	}
	

	@Test
	public void getAllProjects() {
		List<Project> prjctList=new ArrayList<Project>();
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		prjctList.add(prjct);
		when(projectManagerRepository.findAll()).thenReturn(prjctList);
		List<ProjectVO> prjctVO=target.getAllProjects();
		
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
		
		target.saveProject(project);
	}
	
	@Test
	public void getProject() {
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		Optional<Project> a = Optional.ofNullable(prjct);
		when(projectManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		ProjectVO projectVO=target.getProject("1");
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
		target.updateProject(project);
	}
	
	@Test
	public void getAllUsers() {
		List<User> userList=new ArrayList<User>();
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userList.add(user);
		
		when(userManagerRepository.findAll()).thenReturn(userList);
		 List<UserVO> pt=target.getAllUsers();
		
	}
	
	@Test
	public void saveUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		 target.saveUser(user);
		
	}
	@Test
	public void getUser() {
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		Optional<User> a = Optional.ofNullable(user);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(a);
		UserVO userVO=target.getUser("1");
	}

	@Test
	public void updateUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		target.updateUser(user);
	}

}
