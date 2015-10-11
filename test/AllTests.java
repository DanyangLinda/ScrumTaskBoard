

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompleteStoryExecuteTest.class, CreateStoryExecuteTest.class,
		CreateTaskExcecuteTest.class, DeleteStoryExecuteTest.class,
		DeleteTaskExecuteTest.class, MoveTaskExecuteTest.class,
		UpdateTaskExecuteTest.class })
public class AllTests {

}
