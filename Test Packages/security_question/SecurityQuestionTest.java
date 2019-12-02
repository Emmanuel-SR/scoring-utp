package security_question;

import dtos.SecurityQuestion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import services.SecurityQuestionService;
import util.Assert;

public class SecurityQuestionTest {

    private static final SecurityQuestionService SERVICE = new SecurityQuestionService();

    public static void main(String[] args) {
        findAll();
    }

    private static void findAll() {
        Map<String, String> errors = new HashMap<>();
        List<SecurityQuestion> questions = SERVICE.findAll(errors);
        Assert.notNull(questions, errors.get("error"));
    }
}
