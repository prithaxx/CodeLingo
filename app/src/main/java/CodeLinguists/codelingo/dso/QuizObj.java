package CodeLinguists.codelingo.dso;

import java.util.List;

public record QuizObj(int id,
                      int chapterId,
                      QuestionType type,
                      String prompt,
                      boolean hasAnswer,
                      String answer,
                      List<String> hints,
                      List<String> wrongAnswers,
                      String wrongFeedback,
                      String correctFeedback) {
    public static QuizObj cloneAsFeedback(QuizObj quiz, QuestionType type) {
        return new QuizObj(
                quiz.id(),
                quiz.chapterId(),
                type,
                quiz.prompt(),
                false, //feedback does not expect a response
                quiz.answer(),
                quiz.hints(),
                quiz.wrongAnswers(),
                quiz.wrongFeedback(),
                quiz.correctFeedback()
        );
    }
}
