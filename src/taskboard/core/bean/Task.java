package taskboard.core.bean;

import java.io.Serializable;

public class Task implements Serializable{

	/**
	 * @author Danyang Li
	 */
	private static final long serialVersionUID = 1L;

	private String storyId;
	
	private String id;
	
	private String description;
	
	private String status = "To Do";

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isCompleted(){
		return "Done".equals(status);
	}
	
	public boolean isNextStatus(String status){
		if("To Do".equals(this.status) && "In Progress".equals(status)){
			return true;
		}
		if("In Progress".equals(this.status) && "Verify".equals(status)){
			return true;
		}
		if("Verify".equals(this.status) && "Done".equals(status)){
			return true;
		}
		return false;
	}
	
	public boolean isPreviousStatus(String status){
		if("Done".equals(this.status) || "To Do".equals(this.status)){
			return false;
		}
		
		if("Verify".equals(this.status) && !"Verify".equals(status)){
			return true;
		}
		
		if("In Progress".equals(this.status) && "To Do".equals(status)){
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Task){
			return this.storyId.equals(((Task) obj).getStoryId()) && this.id.equals(((Task) obj).getId());
		}
		return false;
	}
}
