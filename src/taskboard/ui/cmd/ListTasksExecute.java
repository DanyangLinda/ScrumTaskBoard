package taskboard.ui.cmd;

import java.util.List;
import taskboard.core.bean.Task;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class ListTasksExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		//List<Task> tasks = TaskBoardDao.getInstance().listTasks(parameter);
		List<Task> tasks = dao.listTasks(parameter);
		if(tasks != null){
			for(Task task : tasks){
				System.out.println(task.getStoryId() + " " + task.getId() + " " + task.getStatus() + " " + task.getDescription());
			}			
		}
		return 0;
	}

	@Override
	public String getPrefixTag() {
		return "list tasks ";
	}

}
