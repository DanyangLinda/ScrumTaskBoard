package taskboard.ui.cmd;

import taskboard.core.bean.Task;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class MoveTaskExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		String[] params = parameter.split(" ");
		if(params.length < 3){
			return 1;
		}
		Task task = new Task();
		task.setStoryId(params[0]);
		task.setId(params[1]);
		task.setStatus(parameter.substring(params[0].length()).trim().substring(params[1].length()).trim());
		//int result = TaskBoardDao.getInstance().moveTask(task);
		int result = dao.moveTask(task);
		/*if(result == 0){
			System.out.println(this.getPrefixTag() + parameter + " successfully");
		}*/
		return result;
	}

	@Override
	public String getPrefixTag() {
		return "move task ";
	}

}
