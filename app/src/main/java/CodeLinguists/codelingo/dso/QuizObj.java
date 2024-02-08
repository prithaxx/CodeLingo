package CodeLinguists.codelingo.dso;

import java.util.List;

public class QuizObj {

    private final int id;
    private final ChapterObj chapter;
    private final String prompt;
    private final String answer;
    private final List<String> wrongAnswers;
    private final List<String> hints;

    public QuizObj(int id, ChapterObj chapter, String prompt, String answer, List<String> hints, List<String> wrongAnswers) {
        this.id = id;
        this.chapter = chapter;
        this.prompt = prompt;
        this.answer = answer;
        this.hints = hints;
        this.wrongAnswers = wrongAnswers;
    }

    public int getId() {
        return id;
    }

    public ChapterObj getChapter() {
        return chapter;
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
