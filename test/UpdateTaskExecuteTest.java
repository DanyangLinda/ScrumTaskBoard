

import static org.junit.Assert.*;

import org.junit.Test;

import taskboard.core.bean.Task;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.CreateTaskExecute;
import taskboard.ui.cmd.UpdateTaskExecute;

public class UpdateTaskExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new UpdateTaskExecute();
		cmd = "story2 task1 update task for story2";
		assertEquals(0, execute.execute(cmd));
		
		Task task = TaskBoardDao.getInstance().listTasks("story2").get(0);
		assertEquals("update task for story2", task.getDescription());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}

}
