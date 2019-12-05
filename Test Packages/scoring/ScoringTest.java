package scoring;

import dtos.Scoring;
import java.util.HashMap;
import java.util.Map;
import services.ScoringService;

import util.Assert;

/**
 *
 * @author esirv
 */
public class ScoringTest {

    private static final ScoringService SERVICE = new ScoringService();

    public static void main(String[] args) {
        create();
    }

    private static void create() {
        Map<String, String> errors = new HashMap<>();
        Scoring scoring = new Scoring();
        scoring.setClarity(3);
        scoring.setHelp(3);
        scoring.setFacility(3);
        scoring.setAssistance(true);
        scoring.setRecommed(true);
        scoring.setCourse("TEORIA GENERAL DE SISTEMAS");
        scoring.setComment("UNA CRACK EN SU CURSO.");
        scoring.setProfessorId(1);
        scoring.setUserId(2);
        scoring.setStudentScore(10);
        Assert.notNull(SERVICE.create(scoring, errors), errors.get("error"));
    }
}
