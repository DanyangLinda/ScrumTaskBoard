package taskboard.ui.cmd;

import taskboard.core.bean.Story;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class CreateStoryExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		String[] params = parameter.split(" ");
		Story story = new Story();
		story.setId(params[0]);
		story.setDescription(parameter.substring(params[0].length()).trim());
		//int result = TaskBoardDao.getInstance().createStory(story);
		int result = dao.createStory(story);
		/*if(result != 0){
			System.out.println("1: Error! This story has alrealy existed.");
		}*/
		
		return result;
	}

	public String getPrefixTag(){
		return "create story ";
	}
}
