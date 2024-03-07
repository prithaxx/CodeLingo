package codelinguists.codelingo.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.QuizIterator;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.stubs.ChapterDataStub;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;

public class QuizHandlerTest {
    List<QuizObj> quizList;
    QuizDataStub quizDataStub;
    IQuizIterator quizIterator;
    private QuizHandler quizHandler;

    @Before
    public void setUp() throws Exception {
        quizDataStub = new QuizDataStub();
        ChapterDataStub chapterDataStub = new ChapterDataStub();
        quizList = new ArrayList<>();
        quizHandler = new QuizHandler(new QuizDataStub());
    }

    @Test
    public void testGetQuiz(){
        int courseId = 1;
        int chapterId = 1;
        quizIterator = quizHandler.getQuiz(courseId, chapterId);

        assertNotNull(quizIterator);
        assertTrue(quizIterator.hasNextQuestion());
    }

    @Test
    public void testCheckQuizAnswer() throws InputValidationException {

        int quizId = 3;
        int chapterId = 1;
        QuizObj quiz = quizDataStub.getQuizById(quizId, chapterId);

        assertTrue(quizHandler.checkQuizAnswer(quiz, "Correct answer"));
        assertFalse(quizHandler.checkQuizAnswer(quiz, "Incorrect Answer 1"));
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
            quizList.add(new QuizObj(1, 1, QuestionType.FEEDBACK_PASSED, "desc", true, "answer",null,null,"wrong","correct"));
        }
    }
}
