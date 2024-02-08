package CodeLinguists.codelingo.dso;

import java.util.List;

public class QuizObj {

    private final int id;
    private final int chapterId;
    private final String prompt;
    private final String answer;
    private final List<String> wrongAnswers;
    private final List<String> hints;

    public QuizObj(int id, int chapterId, String prompt, String answer, List<String> hints, List<String> wrongAnswers) {
        this.id = id;
        this.chapterId = chapterId;
        this.prompt = prompt;
        this.answer = answer;
        this.hints = hints;
        this.wrongAnswers = wrongAnswers;
    }

    public int getId() {
        return id;
    }

    public int getChapterId() {
        return this.chapterId;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getHints() {
        return hints;
    }
    public List<String> getWrongAnswers() {return wrongAnswers;}
}
