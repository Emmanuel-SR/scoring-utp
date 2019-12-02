package services;

import daos.ProfessorDao;
import dtos.Professor;
import dtos.ProfessorScoringDetail;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import java.util.List;
import java.util.Map;

public class ProfessorService {

    private final ProfessorDao dao = new ProfessorDao();

    public ProfessorService() {
    }

    public List<Professor> findAllByText(String text, int limit, Map<String, String> result) {
        List<Professor> professors = null;
        try {
            professors = dao.allByText(text, limit);
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return professors;
    }
    
      public List<ProfessorScoringDetail> findAllScoringDetailsByText(String text, int limit, Map<String, String> result) {
        List<ProfessorScoringDetail> details = null;
        try {
            details = dao.allScoringDetailsByText(text, limit);
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return details;
    }


    public Professor create(Professor professor, Map<String, String> result) {
        try {
            if (dao.insert(professor)) {
                return professor;
            }
        } catch (IncorrectDaoOperation | DuplicateEntryDaoOperation ex) {
            if (ex instanceof DuplicateEntryDaoOperation) {
                result.put("warning", "The professor already exists.");
            } else {
                result.put("error", ex.getMessage());
            }
        }

        return null;
    }

    public Professor findOne(long id, Map<String, String> result) {
        Professor professor = null;
        try {
            professor = dao.get(id);
            if (professor == null) {
                result.put("warning", "No professor found with id: " + id);
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return professor;
    }

}
