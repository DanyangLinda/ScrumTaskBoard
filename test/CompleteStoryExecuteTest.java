

import static org.junit.Assert.*;
import org.junit.Test;

import taskboard.core.bean.Story;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CompleteStoryExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.CreateTaskExecute;
import taskboard.ui.cmd.MoveTaskExecute;

public class CompleteStoryExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story";
		CMDExecute execute = new CreateStoryExecute();
		execute.execute(cmd);
		cmd = "story2 task1 new task for story2";
		execute = new CreateTaskExecute();
		execute.execute(cmd);
		
		execute = new CompleteStoryExecute();
		assertEquals(1, execute.execute("story2"));
		Story story = TaskBoardDao.getInstance().getStory("story2");
		
		assertTrue(!story.getCompleted());
		execute = new MoveTaskExecute();
		assertEquals(0, execute.execute("story2 task1 In Progress"));
		assertEquals(0, execute.execute("story2 task1 Verify"));
		assertEquals(0, execute.execute("story2 task1 Done"));
		
		execute = new CompleteStoryExecute();
		assertEquals(0, execute.execute("story2"));
		story = TaskBoardDao.getInstance().getStory("story2");
		
		assertTrue(story.getCompleted());
		TaskBoardDao.getInstance().deleteStory("story2");
	}

}
