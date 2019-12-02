package services;

import daos.ScoringDao;
import dtos.Scoring;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import java.util.Map;

public class ScoringService {

    private final ScoringDao dao = new ScoringDao();

    public ScoringService() {
    }

    public Scoring create(Scoring scoring, Map<String, String> result) {
        try {
            if (dao.insert(scoring)) {
                return scoring;
            }
        } catch (IncorrectDaoOperation | DuplicateEntryDaoOperation ex) {
            if (ex instanceof DuplicateEntryDaoOperation) {
                result.put("warning", "The scoring already exists.");
            } else {
                result.put("error", ex.getMessage());
            }
        }

        return null;
    }

}
