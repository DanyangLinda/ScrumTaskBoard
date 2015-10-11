

import static org.junit.Assert.*;

import org.junit.Test;

import taskboard.core.bean.Story;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;
import taskboard.ui.cmd.DeleteStoryExecute;

public class DeleteStoryExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story1");
		CMDExecute execute = new CreateStoryExecute();
		execute.execute("story1 new story for test");
		execute = new DeleteStoryExecute();
		assertEquals(0, execute.execute("story1"));
		
		Story story = TaskBoardDao.getInstance().getStory("story1");
		assertNull(story);
		
		TaskBoardDao.getInstance().deleteStory("story1");
	}

}
