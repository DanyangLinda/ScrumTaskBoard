package taskboard.ui;

import taskboard.ui.cmd.CMDUIMain;
/**
 * @author Danyang Li
 */
public class UIFactory {

	/**
	 * @param type UI
	 * @return
	 */
	public static UIMain buildUI(int type){
		return new CMDUIMain();
	}
	
}
