package tn.iit.printModels;

/**
 * User.java
 * This is a model class represents a User entity
 * @author [Your Name]
 *
 */
public class Subject {
    protected int subject_id;
    protected String subject_name;

    
    public Subject(int subject_id, String subject_name) {
		super();
		this.subject_id = subject_id;
		this.subject_name = subject_name;
	}


	public int getSubject_id() {
		return subject_id;
	}


	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


	public String getSubject_name() {
		return subject_name;
	}


	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}





    

    
}
