package dtos;

/**
 *
 * @author esirv
 */
public class Scoring {

    private long scoringId;
    private String course;
    private int clarity;
    private int help;
    private int facility;
    private boolean recommed;
    private boolean assistance;
    private String comment;
    private String studentScore;
    private long professorId;
    private long userId;

    public Scoring() {
    }

    public long getScoringId() {
        return scoringId;
    }

    public void setScoringId(long scoringId) {
        this.scoringId = scoringId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public int getHelp() {
        return help;
    }

    public void setHelp(int help) {
        this.help = help;
    }

    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    public boolean isRecommed() {
        return recommed;
    }

    public void setRecommed(boolean recommed) {
        this.recommed = recommed;
    }

    public boolean isAssistance() {
        return assistance;
    }

    public void setAssistance(boolean assistance) {
        this.assistance = assistance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
