

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import taskboard.core.bean.Task;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.CreateTaskExecute;
import taskboard.ui.cmd.DeleteTaskExecute;

public class DeleteTaskExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		List<Task> tasks = TaskBoardDao.getInstance().listTasks("story2");
		assertEquals(1, tasks.size());
		
		execute = new DeleteTaskExecute();
		assertEquals(0, execute.execute("story2 task1"));
		tasks = TaskBoardDao.getInstance().listTasks("story2");
		assertEquals(0, tasks.size());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}

}
