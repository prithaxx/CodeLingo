package codelinguists.codelingo.unit_tests.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.QuizIterator;
import CodeLinguists.codelingo.logic.QuizIteratorFactory;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;

public class QuizIteratorTest {
    private QuizIterator quizIterator;

    @Before
    public void setUp() {
        QuizDataStub quizDataStub = new QuizDataStub();
        QuizHandler quizHandler = new QuizHandler(quizDataStub, new QuizIteratorFactory());
        quizIterator = new QuizIterator(quizHandler, quizDataStub.getQuizList());
    }

    @Test
    public void testStartQuiz() {
        QuizObj firstQuiz = quizIterator.startQuiz();
        assertNotNull(firstQuiz);
    }

    @Test
    public void testHasNextQuestion() {
        QuizObj firstQuiz = quizIterator.startQuiz();
        assertTrue(quizIterator.hasNextQuestion());
    }
    @Test
    public void testHasNextQuestionFalse() {
        quizIterator.startQuiz();
        while(quizIterator.hasNextQuestion()) {
            quizIterator.nextQuestion();
        }
        assertFalse(quizIterator.hasNextQuestion());
    }

    @Test
    public void testNextQuestion() {
        quizIterator.startQuiz();
        assertNotNull(quizIterator.nextQuestion());
    }

    @Test
    public void testNextQuestionNull() {
        quizIterator.startQuiz();
        while(quizIterator.hasNextQuestion()) {
            quizIterator.nextQuestion();
        }
        assertNull(quizIterator.nextQuestion());
    }

    @Test
    public void testHasPrevQuestion() {
        QuizObj firstQuiz = quizIterator.startQuiz();
        quizIterator.nextQuestion();
        assertTrue(quizIterator.hasPrevQuestion());
    }
    @Test
    public void testHasPrevQuestionFalse() {
        quizIterator.startQuiz();
        assertFalse(quizIterator.hasPrevQuestion());
    }

    @Test
    public void testPrevQuestion() {
        QuizObj firstQ = quizIterator.startQuiz();
        quizIterator.nextQuestion();
        assertEquals(firstQ, quizIterator.prevQuestion());
    }

    @Test
    public void testPrevQuestionNull() {
        quizIterator.startQuiz();
        assertNull(quizIterator.prevQuestion());
    }

    @Test
    public void testSubmitNoInputNeeded() throws InputValidationException {
        quizIterator.startQuiz();
        QuizObj submitResponse = quizIterator.submit("");
        assertSame(QuestionType.TEXT, submitResponse.type()); //Q2 is TEXT
        assertEquals(2, quizIterator.cursorPos());
    }

    @Test (expected = InputValidationException.class)
    public void testSubmitEmptyInput() throws InputValidationException {
        quizIterator.startQuiz();
        quizIterator.nextQuestion();
        quizIterator.nextQuestion();
        QuizObj submitResponse = quizIterator.submit("");
    }
    @Test (expected = InputValidationException.class)
    public void testSubmitNullInput() throws InputValidationException {
        quizIterator.startQuiz();
        quizIterator.nextQuestion();
        quizIterator.nextQuestion();
        QuizObj submitResponse = quizIterator.submit(null);
    }

    @Test
    public void testSubmitWrongInput() throws InputValidationException {
        quizIterator.startQuiz();
        quizIterator.nextQuestion();
        quizIterator.nextQuestion();
        QuizObj submitResponse = quizIterator.submit("Incorrect Answer 1");
        assertSame(QuestionType.FEEDBACK_FAILED, submitResponse.type());
    }
    @Test
    public void testSubmitCorrectInput() throws InputValidationException {
        quizIterator.startQuiz();
        quizIterator.nextQuestion();
        quizIterator.nextQuestion();
        QuizObj submitResponse = quizIterator.submit("Correct answer");
        assertSame(QuestionType.FEEDBACK_PASSED, submitResponse.type());
    }

    @Test
    public void testSubmitNextAfterSubmit() throws InputValidationException {
        quizIterator.startQuiz();
        quizIterator.nextQuestion();
        quizIterator.nextQuestion();
        quizIterator.submit("Correct answer");
        assertNotNull(quizIterator.nextQuestion());
    }
}
