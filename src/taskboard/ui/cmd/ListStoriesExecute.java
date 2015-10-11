package taskboard.ui.cmd;

import java.util.List;

import taskboard.core.bean.Story;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyan Li
 *
 */
public class ListStoriesExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		//List<Story> stories = TaskBoardDao.getInstance().listStories();
		List<Story> stories = dao.listStories();
		for(Story story : stories){
			if(story.getCompleted()){
				System.out.println(story.getId() + " Completed " + story.getDescription());				
			}else{
				System.out.println(story.getId() + " UnCompleted " + story.getDescription());								
			}
		}
		return 0;
	}

	@Override
	public String getPrefixTag() {
		return "list stories";
	}

}
