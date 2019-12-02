package dtos;

/**
 *
 * @author esirv
 */
public class ProfessorScoringDetail {

    private long professorId;
    private String professorFullName;
    private int scorings;
    private double clarityAverage;
    private double helpAverage;
    private double facilityAverage;
    private double totalAverage;

    public ProfessorScoringDetail(long professorId, String professorFullName, int scorings, double clarityAverage, double helpAverage, double facilityAverage, double totalAverage ) {
        this.professorId = professorId;
        this.professorFullName = professorFullName;
        this.scorings = scorings;
        this.clarityAverage = clarityAverage;
        this.helpAverage = helpAverage;
        this.facilityAverage = facilityAverage;
        this.totalAverage = totalAverage;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorFullName() {
        return professorFullName;
    }

    public void setProfessorFullName(String professorFullName) {
        this.professorFullName = professorFullName;
    }

    public int getScorings() {
        return scorings;
    }

    public void setScorings(int scorings) {
        this.scorings = scorings;
    }

    public double getClarityAverage() {
        return clarityAverage;
    }

    public void setClarityAverage(double clarityAverage) {
        this.clarityAverage = clarityAverage;
    }

    public double getHelpAverage() {
        return helpAverage;
    }

    public void setHelpAverage(double helpAverage) {
        this.helpAverage = helpAverage;
    }

    public double getFacilityAverage() {
        return facilityAverage;
    }

    public void setFacilityAverage(double facilityAverage) {
        this.facilityAverage = facilityAverage;
    }

    public double getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(double totalAverage) {
        this.totalAverage = totalAverage;
    }

}
