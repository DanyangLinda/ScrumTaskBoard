

import static org.junit.Assert.*;

import org.junit.Test;

import taskboard.core.bean.Task;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.CreateTaskExecute;
import taskboard.ui.cmd.MoveTaskExecute;

public class MoveTaskExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new MoveTaskExecute();
		assertEquals(0, execute.execute("story2 task1 In Progress"));
		
		Task task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("In Progress", task.getStatus());
		
		assertEquals(0, execute.execute("story2 task1 Verify"));
		
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("Verify", task.getStatus());
		
		assertEquals(0, execute.execute("story2 task1 Done"));
		
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("Done", task.getStatus());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}

	@Test
	public void testExecuteForDone() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new MoveTaskExecute();
		execute.execute("story2 task1 In Progress");
		execute.execute("story2 task1 Verify");
		execute.execute("story2 task1 Done");
		
		assertEquals(1, execute.execute("story2 task1 In Progress"));
		Task task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("Done", task.getStatus());	
		
		assertEquals(1, execute.execute("story2 task1 Verify"));
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("Done", task.getStatus());
		
		assertEquals(1, execute.execute("story2 task1 To Do"));
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("Done", task.getStatus());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
	
	@Test
	public void testExecuteForVerify() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new MoveTaskExecute();
		execute.execute("story2 task1 In Progress");
		execute.execute("story2 task1 Verify");
		
		assertEquals(0, execute.execute("story2 task1 In Progress"));
		Task task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("In Progress", task.getStatus());	
		
		execute.execute("story2 task1 Verify");
		
		assertEquals(0, execute.execute("story2 task1 To Do"));
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("To Do", task.getStatus());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
	
	@Test
	public void testExecuteForInProgress() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new MoveTaskExecute();
		execute.execute("story2 task1 In Progress");
		
		assertEquals(1, execute.execute("story2 task1 Done"));
		Task task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("In Progress", task.getStatus());	
		
		assertEquals(0, execute.execute("story2 task1 To Do"));
		task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("To Do", task.getStatus());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
}
