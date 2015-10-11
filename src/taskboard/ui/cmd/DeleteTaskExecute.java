package taskboard.ui.cmd;

import taskboard.core.bean.Task;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class DeleteTaskExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		String[] params = parameter.split(" ");
		if(params.length != 2){
			return 1;
		}
		Task task = new Task();
		task.setStoryId(params[0]);
		task.setId(params[1]);
		//int result = TaskBoardDao.getInstance().deleteTask(task);
		int result = dao.deleteTask(task);
		/*if(result != 0){
			System.out.println("1: Error! The story does not exist.");
		}*/
		return result;
	}

	@Override
	public String getPrefixTag() {
		return "delete task ";
	}

}
