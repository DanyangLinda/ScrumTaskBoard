

import static org.junit.Assert.*;

import org.junit.Test;

import taskboard.core.bean.Story;
import taskboard.core.dao.TaskBoardDao;
import taskboard.ui.cmd.CMDExecute;
import taskboard.ui.cmd.CreateStoryExecute;

public class CreateStoryExecuteTest {

	@Test
	public void testExecute() {
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story for test";
		CMDExecute execute = new CreateStoryExecute();
		assertEquals(0, execute.execute(cmd));
		
		Story story = TaskBoardDao.getInstance().getStory("story2");
		assertNotNull(story);
		assertEquals("new story for test", story.getDescription());
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
	
	@Test
	public void testExecuteForExistStory(){
		TaskBoardDao.getInstance().deleteStory("story2");
		String cmd = "story2 new story for test";
		CMDExecute execute = new CreateStoryExecute();
		assertEquals(0, execute.execute(cmd));
		
		execute = new CreateStoryExecute();
		assertEquals(1, execute.execute(cmd));
		
		TaskBoardDao.getInstance().deleteStory("story2");
	}
}
