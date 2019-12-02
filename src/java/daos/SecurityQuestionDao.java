package daos;

import dtos.SecurityQuestion;
import exceptions.IncorrectDaoOperation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esirv
 */
public class SecurityQuestionDao extends Dao {

    private static final String SQL_ALL = "select * from security_questions";
    private static final String SQL_GET_BY_ID = "select * from security_questions where security_id=?";

    public List<SecurityQuestion> all() {
        List<SecurityQuestion> questions = new ArrayList<>();
        try {
            ps = cnn.getConnection().prepareStatement(SQL_ALL);
            res = ps.executeQuery();
            while (res.next()) {
                SecurityQuestion question = new SecurityQuestion(res.getLong("security_id"), res.getString("security_text"));
                questions.add(question);
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }finally {
            closeConnection();
        }
        return questions;
    }

    public SecurityQuestion get(Long id) {
        SecurityQuestion question = null;
        try {
            ps = cnn.getConnection().prepareStatement(SQL_GET_BY_ID);
            ps.setLong(1, id);
            res = ps.executeQuery();
            while (res.next()) {
                question = new SecurityQuestion(res.getLong("security_id"),
                        res.getString("security_text"));
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }finally {
            closeConnection();
        }
        return question;
    }

}
