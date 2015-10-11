

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import taskboard.core.bean.Task;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.CreateTaskExecute;

public class CreateTaskExcecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		assertEquals(0, execute.execute(cmd));
		
		List<Task> tasks = TaskBoardDao.getInstance().getStory("story2").getTasks();
		
		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		
		assertEquals("task1", tasks.get(0).getId());
		assertEquals("new task for story2", tasks.get(0).getDescription());
		assertEquals("To Do", tasks.get(0).getStatus());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}

	@Test
	public void testExecuteForExistTask() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		assertEquals(0, execute.execute(cmd));
		assertEquals(1, execute.execute(cmd));
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
}
