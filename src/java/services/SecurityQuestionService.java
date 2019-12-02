package services;

import daos.SecurityQuestionDao;
import dtos.SecurityQuestion;
import exceptions.IncorrectDaoOperation;
import java.util.List;
import java.util.Map;

public class SecurityQuestionService {

    private final SecurityQuestionDao dao = new SecurityQuestionDao();

    public SecurityQuestionService() {
    }

    public SecurityQuestion findOne(long id, Map<String, String> result) {
        SecurityQuestion question = null;
        try {
            question = dao.get(id);
            if (question == null) {
                result.put("warning", "No question found with id: " + id);
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return question;
    }

    public List<SecurityQuestion> findAll(Map<String, String> result) {
        List<SecurityQuestion> questions = null;
        try {
            questions = dao.all();
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return questions;
    }

}
