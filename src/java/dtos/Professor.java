package dtos;

/**
 *
 * @author esirv
 */
public class Professor {

    private long id;
    private String utpCode;
    private String firstName;
    private String lastName;
    private String faculty;
   
    public Professor(String utpCode, String firstname, String lastname, String faculty) {
        this.utpCode = utpCode;
        this.firstName = firstname;
        this.lastName = lastname;
        this.faculty = faculty;
    }

    public Professor(long id, String utpCode, String firstname, String lastname, String faculty) {
        this(utpCode, firstname, lastname, faculty);
        this.id = id;
    }

    public Professor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUtpCode() {
        return utpCode;
    }

    public void setUtpCode(String utpCode) {
        this.utpCode = utpCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
