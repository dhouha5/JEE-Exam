package tn.iit.printModels;

/**
 * User.java
 * This is a model class represents a User entity
 * @author [Your Name]
 *
 */
public class User {
    protected int id;
    protected String uname;
    protected String upwd;
    protected String uemail;
    protected String umobile;
    protected String role;
    
    public User() {
    }
    
    public User(String uname, String upwd, String uemail, String umobile, String role) {
        super();
        this.uname = uname;
        this.upwd = upwd;
        this.uemail = uemail;
        this.umobile = umobile;
        this.role = role;
    }

    public User(int id, String uname, String upwd, String uemail, String umobile, String role) {
        super();
        this.id = id;
        this.uname = uname;
        this.upwd = upwd;
        this.uemail = uemail;
        this.umobile = umobile;
        this.role = role;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
    public String getUemail() {
        return uemail;
    }
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
    public String getUmobile() {
        return umobile;
    }
    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
