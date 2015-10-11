package taskboard.ui.cmd;

//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class CompleteStoryExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		//int result = TaskBoardDao.getInstance().completeStory(parameter);
		int result = dao.completeStory(parameter);
		/*if(result == 0){
			System.out.println("0: success!");
		}*/
		/*if(result!=0){
			System.out.println("1: Error! Story may not exist or contain uncompleted tasks.");
		}*/
		return result;
	}

	@Override
	public String getPrefixTag() {
		return "complete story ";
	}

}
