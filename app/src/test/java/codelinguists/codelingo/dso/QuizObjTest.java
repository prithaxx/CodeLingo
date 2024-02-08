package codelinguists.codelingo.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;

public class QuizObjTest {
    @Test
    public void allValues() {
        QuizObj quizObj = new QuizObj(1, 1, "name", "desc", null, null);
        assertEquals(quizObj.getId(), 1);
        assertEquals(quizObj.getChapterId(), 1);
        assertEquals(quizObj.getPrompt(), "name");
        assertEquals(quizObj.getAnswer(), "desc");
        assertNull(quizObj.getHints());
        assertNull(quizObj.getWrongAnswers());
    }
}
