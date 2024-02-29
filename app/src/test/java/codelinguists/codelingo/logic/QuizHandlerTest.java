package codelinguists.codelingo.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.QuizIterator;

public class QuizHandlerTest {
    List<QuizObj> quizList;

    @Before
    public void setUp() throws Exception {
        quizList = new ArrayList<>();
    }

    @Test
    public void emptyList() {
        QuizIterator quizHandler = new QuizIterator(quizList);

        assertFalse(quizHandler.hasPrevQuestion());
        assertFalse(quizHandler.hasNextQuestion());

        assertNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 0);
        assertNull(quizHandler.prevQuestion());
        assertEquals(quizHandler.cursorPos(), 0);
    }

    @Test
    public void nullList() {
        QuizIterator quizHandler = new QuizIterator(null);

        assertFalse(quizHandler.hasPrevQuestion());
        assertFalse(quizHandler.hasNextQuestion());

        assertNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 0);
        assertNull(quizHandler.prevQuestion());
        assertEquals(quizHandler.cursorPos(), 0);
    }

    @Test
    public void oneElement() {
        addQuizObj(1);
        QuizIterator quizHandler = new QuizIterator(quizList);

        assertFalse(quizHandler.hasPrevQuestion());
        assertTrue(quizHandler.hasNextQuestion());

        assertNotNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
        assertFalse(quizHandler.hasPrevQuestion());
        assertNull(quizHandler.prevQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
        assertNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
    }

    @Test
    public void twoElement() {
        addQuizObj(2);
        QuizIterator quizHandler = new QuizIterator(quizList);

        assertFalse(quizHandler.hasPrevQuestion());
        assertTrue(quizHandler.hasNextQuestion());

        assertNotNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
        assertNull(quizHandler.prevQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
        assertNotNull(quizHandler.nextQuestion());
        assertEquals(quizHandler.cursorPos(), 2);
        assertNull(quizHandler.nextQuestion());
        assertTrue(quizHandler.hasPrevQuestion());
        assertEquals(quizHandler.cursorPos(), 2);
        assertNotNull(quizHandler.prevQuestion());
        assertEquals(quizHandler.cursorPos(), 1);
    }

    private void addQuizObj(int count) {
        for (int i = 0; i < count; i++) {
            quizList.add(new QuizObj(1, 1, QuestionTypes.FEEDBACK_PASSED, "desc", true, "answer",null,null,"wrong","correct"));
        }
    }
}
