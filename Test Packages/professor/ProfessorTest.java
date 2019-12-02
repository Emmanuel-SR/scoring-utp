package professor;

import dtos.Professor;
import dtos.ProfessorScoringDetail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import services.ProfessorService;

import util.Assert;

/**
 *
 * @author esirv
 */
public class ProfessorTest {

    private static final ProfessorService SERVICE = new ProfessorService();

    public static void main(String[] args) {
        //findAllByText();
        //create();
        //findOne();
        findAllScoringDetailsByText();
    }

    private static void findAllByText() {
        Map<String, String> errors = new HashMap<>();
        List<Professor> professor = SERVICE.findAllByText("janet", 10, errors);
        Assert.notNull(professor, errors.get("error"));
    }

    private static void findAllScoringDetailsByText() {
        Map<String, String> errors = new HashMap<>();
        List<ProfessorScoringDetail> details = SERVICE.findAllScoringDetailsByText("janet", 10, errors);
        Assert.notNull(details, errors.get("error"));
    }

    private static void findOne() {
        Map<String, String> errors = new HashMap<>();
        Professor professor = SERVICE.findOne(1, errors);
        Assert.notNull(professor, errors.get("error"));
    }

    private static void create() {
        Map<String, String> errors = new HashMap<>();
        Professor professor = new Professor("C02287", "ROXANA JANET", "QUIROZ VALENZUELA", "Ingenieria");
        Assert.notNull(SERVICE.create(professor, errors), errors.get("warning"));
    }
}
