package com.fsd.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.fsd.projectmanager.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	private ParentManagerRepository parentManagerRepository;
	
	@Autowired
	private UserManagerRepository userManagerRepository;
	
	
	public List<TaskVO> getAllTasks(){
		
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		List<Task> tasks =taskManagerRepository.findAll();
		for(Task task: tasks) {
			TaskVO taskVO=new TaskVO();
			taskVO.setTaskId(task.getTaskId());
			taskVO.setTaskName(task.getTaskName());
			taskVO.setParentTaskId(task.getParentTask().getParentTaskId());
			taskVO.setParentTaskName(task.getParentTask().getParentTaskName());
			taskVO.setProjectId(task.getProjectDetails().getProjectId());
			taskVO.setPriority(task.getPriority());
			taskVO.setStartDate(task.getStartDate());
			taskVO.setEndDate(task.getEndDate());
			taskVO.setStatus(task.getStatus());
			taskVO.setEmployeeId(task.getUserDetails().getEmployeeId());
			taskVOList.add(taskVO);
			
		}
		return taskVOList;
	}

	public void saveTask(TaskVO taskVO) {
		
		Task task=new Task();
		task.setEndDate(taskVO.getEndDate());
		task.setPriority(taskVO.getPriority());
		task.setStartDate(taskVO.getStartDate());
		task.setStatus("A");
		task.setTaskName(taskVO.getTaskName());

		ParentTask parent=new ParentTask();
		if(taskVO.getParentTaskId()!=null) {
			parent=parentManagerRepository.findById(taskVO.getParentTaskId()).get();
		}else {
			parent.setParentTaskName(taskVO.getParentTaskName());
			parent.setProjectId(taskVO.getProjectId());
			parentManagerRepository.save(parent);
		}
		task.setParentTask(parent);
		
		Project prjct=new Project();
		prjct=projectManagerRepository.findById(taskVO.getProjectId()).get();
		task.setProjectDetails(prjct);
		
		User usr=new User();
		usr=userManagerRepository.findById(taskVO.getEmployeeId()).get();
		task.setUserDetails(usr);
		taskManagerRepository.save(task);
	}
	
	public TaskVO getTask(String taskId) {
		Optional<Task> optTask = taskManagerRepository.findById(Long.parseLong(taskId));

		TaskVO taskVO=new TaskVO();
		taskVO.setTaskId(optTask.get().getTaskId());
		taskVO.setTaskName(optTask.get().getTaskName());
		taskVO.setParentTaskId(optTask.get().getParentTask().getParentTaskId());
		taskVO.setParentTaskName(optTask.get().getParentTask().getParentTaskName());
		taskVO.setProjectId(optTask.get().getProjectDetails().getProjectId());
		taskVO.setPriority(optTask.get().getPriority());
		taskVO.setStartDate(optTask.get().getStartDate());
		taskVO.setEndDate(optTask.get().getEndDate());
		taskVO.setStatus(optTask.get().getStatus());
		taskVO.setEmployeeId(optTask.get().getUserDetails().getEmployeeId());

		return taskVO;
	}

	public void updateTask(TaskVO taskVO) {
		Task task=new Task();
		task.setTaskId(taskVO.getTaskId());
		task.setEndDate(taskVO.getEndDate());
		task.setPriority(taskVO.getPriority());
		task.setStartDate(taskVO.getStartDate());
		task.setStatus(taskVO.getStatus());
		task.setTaskName(taskVO.getTaskName());

		ParentTask p= getParentTasks(taskVO.getParentTaskId());
		//p.setParentTaskId(taskVO.getParentTaskId());
		//p.setParentTaskName("tsk screens");
		task.setParentTask(p);
		
		Project pd= projectManagerRepository.findById(taskVO.getProjectId()).get();
		task.setProjectDetails(pd);
		
		User u=userManagerRepository.findById(taskVO.getEmployeeId()).get();
		task.setUserDetails(u);
		
		taskManagerRepository.save(task);
	}

	public List<ParentTaskVO> getAllParentTasks() {
		List<ParentTaskVO> parentTaskVOList = new ArrayList<ParentTaskVO>();
		List<ParentTask> parentTasks =parentManagerRepository.findAll();
		for(ParentTask parentTask: parentTasks) {
			ParentTaskVO parentTaskVO=new ParentTaskVO();
			parentTaskVO.setParentTaskId(parentTask.getParentTaskId());
			parentTaskVO.setParentTaskName(parentTask.getParentTaskName());
			parentTaskVO.setProjectId(parentTask.getProjectId());
			parentTaskVOList.add(parentTaskVO);
			
		}
		return parentTaskVOList;
	}
	
	public ParentTask getParentTasks(Long parentId) {
		Optional<ParentTask> optParent = parentManagerRepository.findById(parentId);
		return optParent.get();
	}

	public List<ProjectVO> getAllProjects() {
		
		List<ProjectVO> projectVOlist = new ArrayList<ProjectVO>();
		List<Project> projects =projectManagerRepository.findAll();
		for(Project project: projects) {
			ProjectVO projectVO=new ProjectVO();
			projectVO.setProjectId(project.getProjectId());
			projectVO.setProjectName(project.getProjectName());
			projectVO.setStartDate(project.getStartDate());
			projectVO.setEndDate(project.getEndDate());
			projectVO.setPriority(project.getPriority());
			projectVO.setStatus(project.getStatus());
			projectVO.setEmployeeId(project.getManagerId());
			Long task=taskManagerRepository.getNoOfTask(project.getProjectId());
 			projectVO.setNoOfTask(task);

			projectVOlist.add(projectVO);
			
		}
		return projectVOlist;
	}

	public void saveProject(ProjectVO projectVO) {
		
		Project project=new Project();
	
		if(projectVO.getProjectId()!=null) {
			project.setProjectId(projectVO.getProjectId());
		}
		project.setProjectName(projectVO.getProjectName());
		project.setStartDate(projectVO.getStartDate());
		project.setEndDate(projectVO.getEndDate());
		project.setPriority(projectVO.getPriority());
		project.setManagerId(projectVO.getEmployeeId());
		project.setStatus(projectVO.getStatus());
		projectManagerRepository.save(project);
	
		
	}

	@Override
	public ProjectVO getProject(String projectId) {
		Optional<Project> optProject = projectManagerRepository.findById(Long.parseLong(projectId));

		ProjectVO projectVO=new ProjectVO();
		projectVO.setProjectId(optProject.get().getProjectId());
		projectVO.setProjectName(optProject.get().getProjectName());
		projectVO.setStartDate(optProject.get().getStartDate());
		projectVO.setEndDate(optProject.get().getEndDate());
		projectVO.setPriority(optProject.get().getPriority());
		projectVO.setStatus(optProject.get().getStatus());
		projectVO.setEmployeeId(optProject.get().getManagerId());
		return projectVO;
	}

	@Override
	public void updateProject(ProjectVO projectVO) {

		Project project=new Project();
		project.setProjectId(projectVO.getProjectId());
		project.setProjectName(projectVO.getProjectName());
		project.setStartDate(projectVO.getStartDate());
		project.setEndDate(projectVO.getEndDate());
		project.setPriority(projectVO.getPriority());
		project.setManagerId(projectVO.getEmployeeId());
		projectManagerRepository.save(project);
		
	
		
	}

	@Override
	public List<UserVO> getAllUsers() {
		
		List<UserVO> userVOList = new ArrayList<UserVO>();
		List<User> users =userManagerRepository.findAll();
		for(User user: users) {
			UserVO userVO=new UserVO();
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setEmployeeId(user.getEmployeeId());
			userVO.setStatus(user.getStatus());

			userVOList.add(userVO);
			
		}
		return userVOList;
	
	}

	@Override
	public void saveUser(UserVO userVO) {
		User user=new User();
		user.setEmployeeId(userVO.getEmployeeId());
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setStatus(userVO.getStatus());
		
		userManagerRepository.save(user);
		
	}

	@Override
	public UserVO getUser(String userId) {
		Optional<User> optUser = userManagerRepository.findById(userId);

		UserVO userVO=new UserVO();
		userVO.setFirstName(optUser.get().getFirstName());
		userVO.setLastName(optUser.get().getLastName());
		userVO.setEmployeeId(optUser.get().getEmployeeId());
		userVO.setStatus(optUser.get().getStatus());
		return userVO;
	}

	@Override
	public void updateUser(UserVO userVO) {
		User user=new User();
		user.setEmployeeId(userVO.getEmployeeId());
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setStatus(userVO.getStatus());
		userManagerRepository.save(user);
		
	}
}
