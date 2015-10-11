package taskboard.ui.cmd;

import taskboard.core.dao.TaskBoardDao;

/**
 * 
 * @author Danyang Li
 *
 */
public interface CMDExecute {
	TaskBoardDao dao = TaskBoardDao.getInstance();
	public int execute(String parameter);	
	public String getPrefixTag();
}
