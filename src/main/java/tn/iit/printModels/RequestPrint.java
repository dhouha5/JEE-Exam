package tn.iit.printModels;

/**
 * User.java
 * This is a model class represents a User entity
 * @author [Your Name]
 *
 */
public class RequestPrint {
    
    protected int subject_id;
    protected String document_name;
    protected String arrival_date;
    protected int num_copies;
	public RequestPrint( int subject_id, String document_name, String arrival_date, int num_copies) {
		super();
		
		this.subject_id = subject_id;
		this.document_name = document_name;
		this.arrival_date = arrival_date;
		this.num_copies = num_copies;
	}
	
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public int getNum_copies() {
		return num_copies;
	}
	public void setNum_copies(int num_copies) {
		this.num_copies = num_copies;
	}
    
    

}
