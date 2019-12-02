/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Scoring;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import java.sql.SQLException;

/**
 *
 * @author esirv
 */
public class ScoringDao extends Dao {

    private static final String SQL_INSERT = "INSERT INTO `scorings` (`course`, `clarity`, `help`, `facility`, `recommend`, `assistance`, `comment`, `student_score`, `professor_id`, `user_id`) VALUES (?,?,?,?,?,?,?,?,?,?)";

    public boolean insert(Scoring scoring) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_INSERT);

            ps.setString(1, scoring.getCourse());
            ps.setInt(2, scoring.getClarity());
            ps.setInt(3, scoring.getHelp());
            ps.setInt(4, scoring.getFacility());
            ps.setBoolean(5, scoring.isRecommed());
            ps.setBoolean(6, scoring.isAssistance());
            ps.setString(7, scoring.getComment());
            ps.setString(8, scoring.getStudentScore());
            ps.setLong(9, scoring.getProfessorId());
            ps.setLong(10, scoring.getUserId());

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
