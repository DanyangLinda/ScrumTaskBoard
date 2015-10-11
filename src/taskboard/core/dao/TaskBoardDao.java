package taskboard.core.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import taskboard.core.bean.Story;
import taskboard.core.bean.Task;
/**
 * 
 * @author Danyang Li
 *
 */
public class TaskBoardDao {

	private static List<Story> stories;
	private static final String DATA_FILE = "story.io";
	static{
		readFromFile();
		if(stories == null){
			stories = new ArrayList<Story>();
		}
	}
	
	public static TaskBoardDao getInstance(){
		return new TaskBoardDao();
	}
	
	public int createStory(Story story){
		if(stories.contains(story)){
			return 1;
		}
		stories.add(story);
		writeToFile();
		return 0;
	}

	public List<Story> listStories(){
		return stories;
	}
	
	public Story getStory(String id){
		int index = lookupStory(id);
		if(index == -1 ){// The story doesn't  exist.
			return null;
		}
		return stories.get(index);
	}
	
	public int deleteStory(String id){
		Story story = new Story();
		story.setId(id);
		stories.remove(story);
		writeToFile();
		return 0;
	}
	
	public int completeStory(String id){
		int index = lookupStory(id);
		if(index == -1){// story not exist
			return 1;
		}
		Story story = stories.get(index);
		List<Task> tasks = story.getTasks();
		for(Task task : tasks){
			if(!task.isCompleted()){// story contains uncompleted tasks.
				return 1;
			}
		}
		story.setCompleted(true);
		writeToFile();
		return 0;
	}
	
	public int createTask(Task task){
		int index = lookupStory(task.getStoryId());
		if(index == -1){// story not exist
			return 1;
		}
		Story story = stories.get(index);
		// if the story has already been completed, no new task should be add to the story
		// if the task has already existed, we don't create it again.
		if(story.getCompleted() || story.getTasks().contains(task)){
			return 1;
		}
		story.getTasks().add(task);
		writeToFile();
		return 0;
	}
	
	public List<Task> listTasks(String storyId){
		int index = lookupStory(storyId);
		if(index == -1){// story not exist
			return null;
		}
		Story story = stories.get(index);
		return story.getTasks();
	}
	
	public int deleteTask(Task task){
		int index = lookupStory(task.getStoryId());		
		if(index == -1){// story not exist
			return 1;
		}
		Story story = stories.get(index);
		story.getTasks().remove(task);
		return 0;
	}
	
	public int moveTask(Task task){
		int index = lookupTask(task);
		if(index == -1){// task not exist
			return 1;
		}
		Story story =  stories.get(index);
		story.setId(task.getStoryId());
		Task oldTask = story.getTasks().get(index);
		if(!oldTask.isNextStatus(task.getStatus()) && !oldTask.isPreviousStatus(task.getStatus())){
			return 1;
		}
		oldTask.setStatus(task.getStatus());
		writeToFile();
		return 0;
	}
	

	public int updateTask(Task task){
		int index = lookupTask(task);
		if(index == -1){//task not exist
			return 1;
		}
		Story story = stories.get(index);
		Task oldTask = story.getTasks().get(index);
		oldTask.setDescription(task.getDescription());
		writeToFile();
		return 0;
	}
	
	private int lookupTask(final Task task) {
		String  storyId = task.getStoryId();
		int index = lookupStory(storyId);
		if(index==-1){
			return -1;
		}
		Story story = new Story();
		story = stories.get(index);
		index = story.getTasks().indexOf(task);
		if(index==-1){
			return -1;
		}
		return index;		
	}
	
	public int lookupStory(String storyId){
		//Because we use indexOf here and we just want compare the ids of story instances, 
		//we need to override the equals method in the Story class.
		Story story = new Story();
		story.setId(storyId);
		int index = stories.indexOf(story);
		if(index == -1){
			return -1;
		}
		return index;
	}
	
	private static synchronized void writeToFile(){
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
			out.writeObject(stories);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(out != null){
					out.close();					
				}
			}catch(IOException e){
				
			}
		}
	}
	
	private static void readFromFile(){
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new FileInputStream(DATA_FILE));
			stories = (List<Story>) in.readObject();
		}catch(IOException e){
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try{
				if(in != null){
					in.close();					
				}
			}catch(IOException e){
				
			}
		}
	}
}
