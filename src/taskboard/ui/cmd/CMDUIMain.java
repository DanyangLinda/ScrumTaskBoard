package taskboard.ui.cmd;

import java.util.Scanner;
/**
 * @author Danyang Li
 */

import taskboard.ui.UIMain;

public class CMDUIMain implements UIMain {

	@Override
	public void startup() {
		System.out.print("input command>>");
		Scanner cin = new Scanner(System.in);
		String line = null;
		CMDExecute[] executes = new CMDExecute[]{new CreateStoryExecute(), new ListStoriesExecute(), new DeleteStoryExecute(), new CompleteStoryExecute(),
				new CreateTaskExecute(), new ListTasksExecute(), new DeleteTaskExecute(), 
				new MoveTaskExecute(), new UpdateTaskExecute()};
		try{
			while(true){
				line = cin.nextLine().trim();
				if(line.equals("exit")){
					break;
				}
				boolean supportCmd = false;
				String cmd = null;
				for(CMDExecute execute : executes){
					cmd = execute.getPrefixTag().trim();
					if(line.startsWith(cmd)){
					 		supportCmd = true;
					 		String parameter = line.substring(cmd.length()).trim();
					  		int result = execute.execute(parameter);
					  		System.out.println(result);
					}
				}
				
				if(!supportCmd){
					System.out.println("command not found");
				}
				System.out.print("input command>>");
			}			
		}finally{
			cin.close();
		}
	}

}
