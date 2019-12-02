/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Professor;
import dtos.ProfessorScoringDetail;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esirv
 */
public class ProfessorDao extends Dao {

    private static final String SQL_ALL_BY_TEXT = "select * from professors where CONCAT(first_name, ' ', last_name) like ? limit ?";
    private static final String SQL_GET_BY_ID = "select * from professors where professor_id = ?";
    private static final String SQL_INSERT = "insert into professors (utp_code,first_name, last_name, faculty) values (?,?,?,?)";
    private static final String SQL_ALL_SCORING_DETAILS_BY_TEXT = "select pr.professor_id,CONCAT(pr.first_name,' ',pr.last_name) AS professor_full_name, IF(count(sc.scoring_id) > 0, count(sc.scoring_id) , null) as scorings, ROUND(avg(sc.facility),1) as facility_avg , ROUND(avg(sc.`help`),1) as help_avg,\n"
            + " ROUND(avg(sc.clarity),1) as clarity_avg, ROUND((avg(sc.clarity) + avg(sc.facility) + avg(sc.`help`)) / 3,1) as total_avg from scorings as sc right join professors as pr on sc.professor_id = pr.professor_id where CONCAT(first_name, ' ', last_name) like ? group by pr.professor_id limit ?";

    public List<Professor> allByText(String text, int limit) {
        List<Professor> professors = new ArrayList<>();
        try {
            ps = cnn.getConnection().prepareStatement(SQL_ALL_BY_TEXT);
            ps.setString(1, "%" + text + "%");
            ps.setInt(2, limit);
            res = ps.executeQuery();
            while (res.next()) {
                Professor professor = new Professor(res.getLong("professor_id"), res.getString("utp_code"), res.getString("first_name"), res.getString("last_name"), res.getString("faculty"));
                professors.add(professor);
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return professors;
    }

    public List<ProfessorScoringDetail> allScoringDetailsByText(String text, int limit) {
        List<ProfessorScoringDetail> details = new ArrayList<>();
        try {
            ps = cnn.getConnection().prepareStatement(SQL_ALL_SCORING_DETAILS_BY_TEXT);
            ps.setString(1, "%" + text + "%");
            ps.setInt(2, limit);
            res = ps.executeQuery();
            while (res.next()) {
                    ProfessorScoringDetail detail = new ProfessorScoringDetail(res.getLong("professor_id"), res.getString("professor_full_name"), res.getInt("scorings"), res.getDouble("facility_avg"), res.getDouble("help_avg"), res.getDouble("clarity_avg"),  res.getDouble("total_avg"));
                    details.add(detail);
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return details;
    }

    public Professor get(long id) {
        Professor professor = null;
        try {
            ps = cnn.getConnection().prepareStatement(SQL_GET_BY_ID);
            ps.setLong(1, id);
            res = ps.executeQuery();
            while (res.next()) {
                professor = new Professor(res.getLong("professor_id"), res.getString("utp_code"), res.getString("first_name"), res.getString("last_name"), res.getString("faculty"));
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return professor;
    }

    public boolean insert(Professor professor) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_INSERT);

            ps.setString(1, professor.getUtpCode());
            ps.setString(2, professor.getFirstName());
            ps.setString(3, professor.getLastName());
            ps.setString(4, professor.getFaculty());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
                throw new DuplicateEntryDaoOperation(e.getMessage());
            } else {
                throw new IncorrectDaoOperation(e.getMessage());
            }
        }

        return false;
    }

}
