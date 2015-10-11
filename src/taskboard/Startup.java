package taskboard;

import taskboard.ui.UIFactory;
/**
 * 
 * @author Danyang Li
 *
 */

public class Startup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIFactory.buildUI(0).startup();
	}

}
