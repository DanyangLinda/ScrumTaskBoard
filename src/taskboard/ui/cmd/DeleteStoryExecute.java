package taskboard.ui.cmd;

//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class DeleteStoryExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		//int result = TaskBoardDao.getInstance().deleteStory(parameter);
		int result = dao.deleteStory(parameter);
		/*if(result != 0){
			System.out.println("1: Error! The story does not exist.");
		}*/
		return result;
	}

	@Override
	public String getPrefixTag() {
		return "delete story ";
	}

}
