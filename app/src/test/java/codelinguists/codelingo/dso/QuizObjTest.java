package codelinguists.codelingo.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import CodeLinguists.codelingo.dso.QuizObj;

public class QuizObjTest {
    @Test
    public void allValues() {
        QuizObj quizObj = new QuizObj(1, 1, "name", "desc", null, null);
        assertEquals(quizObj.id(), 1);
        assertEquals(quizObj.chapterId(), 1);
        assertEquals(quizObj.prompt(), "name");
        assertEquals(quizObj.answer(), "desc");
        assertNull(quizObj.hints());
        assertNull(quizObj.wrongAnswers());
    }
}
