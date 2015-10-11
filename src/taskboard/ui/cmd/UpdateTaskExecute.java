package taskboard.ui.cmd;

import taskboard.core.bean.Task;
//import taskboard.core.dao.TaskBoardDao;
/**
 * 
 * @author Danyang Li
 *
 */
public class UpdateTaskExecute implements CMDExecute {

	@Override
	public int execute(String parameter) {
		String[] params = parameter.split(" ");
		if(params.length < 2){
			return 1;
		}
		Task task = new Task();
		task.setStoryId(params[0]);
		task.setId(params[1]);
		task.setDescription(parameter.substring(params[0].length()).trim().substring(params[1].length()).trim());
		
		//int result = TaskBoardDao.getInstance().updateTask(task);
		int result = dao.updateTask(task);
		/*if(result == 0){
			System.out.println(this.getPrefixTag() + task.getStoryId() + " " + task.getId() + " successfully!");
		}*/
		return result;
	}

	@Override
	public String getPrefixTag() {
		return "update task ";
	}

}
