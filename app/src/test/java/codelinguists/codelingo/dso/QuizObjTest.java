package codelinguists.codelingo.dso;

import static org.junit.Assert.*;

import org.junit.Test;

import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;

public class QuizObjTest {
    @Test
    public void allValues() {
        QuizObj quizObj = new QuizObj(1, 1, QuestionTypes.FEEDBACK_PASSED, "desc", true, "answer",null,null,"wrong","correct");
        assertEquals(quizObj.id(), 1);
        assertEquals(quizObj.chapterId(), 1);
        assertEquals(quizObj.prompt(), "desc");
        assertTrue(quizObj.hasAnswer());
        assertEquals(quizObj.answer(), "answer");
        assertNull(quizObj.hints());
        assertNull(quizObj.wrongAnswers());
        assertEquals(quizObj.wrongFeedback(), "wrong");
        assertEquals(quizObj.correctFeedback(), "correct");
    }
}
