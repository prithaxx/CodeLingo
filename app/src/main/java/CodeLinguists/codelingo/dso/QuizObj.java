package CodeLinguists.codelingo.dso;

import java.util.List;

public record QuizObj(int id,
                      int chapterId,
                      QuestionTypes type,
                      String prompt,
                      String answer,
                      List<String> hints,
                      List<String> wrongAnswers) {}
