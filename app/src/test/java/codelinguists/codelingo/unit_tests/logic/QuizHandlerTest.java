package codelinguists.codelingo.unit_tests.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizNavigation;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.QuizNavigation;
import CodeLinguists.codelingo.logic.QuizIteratorFactory;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;

public class QuizHandlerTest {
    List<QuizObj> quizList;
    QuizDataStub quizDataStub;
    IQuizNavigation quizIterator;
    private QuizHandler quizHandler;

    @Before
    public void setUp(){
        quizDataStub = new QuizDataStub();
        quizList = new ArrayList<>();
        quizHandler = new QuizHandler(quizDataStub, new QuizIteratorFactory());
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
    public void testGetQuizNoChapter(){
        int courseId = 1;
        int chapterId = -1;
        quizIterator = quizHandler.getQuiz(courseId, chapterId);

        assertNotNull(quizIterator);
        assertFalse(quizIterator.hasNextQuestion());
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
        QuizNavigation quizIter = new QuizNavigation(quizHandler, quizList);

        assertFalse(quizIter.hasPrevQuestion());
        assertFalse(quizIter.hasNextQuestion());

        assertNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 0);
        assertNull(quizIter.prevQuestion());
        assertEquals(quizIter.cursorPos(), 0);
    }

    @Test
    public void nullList() {
        QuizNavigation quizIter = new QuizNavigation(quizHandler, null);

        assertFalse(quizIter.hasPrevQuestion());
        assertFalse(quizIter.hasNextQuestion());

        assertNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 0);
        assertNull(quizIter.prevQuestion());
        assertEquals(quizIter.cursorPos(), 0);
    }

    @Test
    public void oneElement() {
        addQuizObj(1);
        QuizNavigation quizIter = new QuizNavigation(quizHandler, quizList);

        assertFalse(quizIter.hasPrevQuestion());
        assertTrue(quizIter.hasNextQuestion());

        assertNotNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 1);
        assertFalse(quizIter.hasPrevQuestion());
        assertNull(quizIter.prevQuestion());
        assertEquals(quizIter.cursorPos(), 1);
        assertNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 1);
    }

    @Test
    public void twoElement() {
        addQuizObj(2);
        QuizNavigation quizIter = new QuizNavigation(quizHandler, quizList);

        assertFalse(quizIter.hasPrevQuestion());
        assertTrue(quizIter.hasNextQuestion());

        assertNotNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 1);
        assertNull(quizIter.prevQuestion());
        assertEquals(quizIter.cursorPos(), 1);
        assertNotNull(quizIter.nextQuestion());
        assertEquals(quizIter.cursorPos(), 2);
        assertNull(quizIter.nextQuestion());
        assertTrue(quizIter.hasPrevQuestion());
        assertEquals(quizIter.cursorPos(), 2);
        assertNotNull(quizIter.prevQuestion());
        assertEquals(quizIter.cursorPos(), 1);
    }

    @Test (expected = InputValidationException.class)
    public void checkAnswerNullQuiz() throws InputValidationException {
        quizHandler.checkQuizAnswer(null, "answer");
    }

    @Test (expected = InputValidationException.class)
    public void checkAnswerNullAnswer() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", true, "answer", null, null, "Wrong", "Right");
        quizHandler.checkQuizAnswer(quizObj, null);
    }

    @Test (expected = InputValidationException.class)
    public void checkAnswerBlankAnswer() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", true, "answer", null, null, "Wrong", "Right");
        quizHandler.checkQuizAnswer(quizObj, "");
    }

    @Test
    public void checkAnswerHasAnswerFalse() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", false, "answer", null, null, "Wrong", "Right");
        assertTrue(quizHandler.checkQuizAnswer(quizObj, ""));
    }

    @Test
    public void checkAnswerHasAnswerFalseNull() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", false, "answer", null, null, "Wrong", "Right");
        assertTrue(quizHandler.checkQuizAnswer(quizObj, null));
    }

    @Test
    public void checkAnswerBlankAnswerField() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", true, "", null, null, "Wrong", "Right");
        assertTrue(quizHandler.checkQuizAnswer(quizObj, null));
    }

    @Test
    public void checkAnswerCorrect() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", true, "answer", null, null, "Wrong", "Right");
        assertTrue(quizHandler.checkQuizAnswer(quizObj, "answer"));
    }

    @Test
    public void checkAnswerWrong() throws InputValidationException {
        QuizObj quizObj = new QuizObj(1, 1, QuestionType.SHORT_ANSWER, "prompt", true, "answer", null, null, "Wrong", "Right");
        assertFalse(quizHandler.checkQuizAnswer(quizObj, "wrong"));
    }

    private void addQuizObj(int count) {
        for (int i = 0; i < count; i++) {
            quizList.add(new QuizObj(1, 1, QuestionType.FEEDBACK_PASSED, "desc", true, "answer",null,null,"wrong","correct"));
        }
    }
}
