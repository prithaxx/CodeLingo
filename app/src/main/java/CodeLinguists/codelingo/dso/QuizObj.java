package CodeLinguists.codelingo.dso;

import java.util.List;

public class QuizObj {

    private final int id;
    private final ChapterObj chapter;
    private final String prompt;
    private final String answer;
    private final List<String> hints;

    public QuizObj(int id, ChapterObj chapter, String prompt, String answer, List<String> hints) {
        this.id = id;
        this.chapter = chapter;
        this.prompt = prompt;
        this.answer = answer;
        this.hints = hints;
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
}
