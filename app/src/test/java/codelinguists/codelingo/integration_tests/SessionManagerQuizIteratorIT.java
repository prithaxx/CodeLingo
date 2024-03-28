package codelinguists.codelingo.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizNavigation;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import codelinguists.codelingo.utils.SqlDbIT;

public class SessionManagerQuizIteratorIT extends SqlDbIT {
    ISessionManager sessionManager;

    //General session manager tests
    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }

    @Test
    public void testQuizValidationSuccess() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        QuizObj quiz = new QuizObj(1,1, QuestionType.SHORT_ANSWER, "", true, "answer",null, null, "wrong", "correct");
        assertTrue(Services.getQuizHandler().checkQuizAnswer(quiz, "answer"));
    }

    @Test
    public void testQuizValidationFailure() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        QuizObj quiz = new QuizObj(1,1, QuestionType.SHORT_ANSWER, "", true, "answer",null, null, "wrong", "correct");
        assertFalse(Services.getQuizHandler().checkQuizAnswer(quiz, "not-answer"));
    }

    @Test(expected = NoItemSelectedException.class)
    public void testQuizIteratorStartQuizUnselected() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.startQuiz();
    }

    @Test
    public void testQuizIteratorStartQuizStart() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        assertNotNull(sessionManager.startQuiz().startQuiz());
    }

    @Test
    public void testQuizIteratorStartQuizHasNext() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        assertTrue(sessionManager.startQuiz().hasNextQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizNext() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        assertNotNull(qi.nextQuestion());
        assertEquals(1, qi.cursorPos());
    }

    @Test
    public void testQuizIteratorStartQuizHasNextFalse() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        while (qi.hasNextQuestion()) {
            qi.nextQuestion();
        }
        assertFalse(qi.hasNextQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizNextNull() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        while (qi.hasNextQuestion()) {
            qi.nextQuestion();
        }
        assertNull(qi.nextQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizHasPreviousFalse() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        assertFalse(qi.hasPrevQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizPreviousNull() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        assertNull(qi.prevQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizHasPrevious() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        qi.nextQuestion();
        qi.nextQuestion();
        assertTrue(qi.hasPrevQuestion());
    }

    @Test
    public void testQuizIteratorStartQuizPrevious() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        qi.nextQuestion();
        qi.nextQuestion();
        assertNotNull(qi.prevQuestion());
    }

    @Test
    public void testQuizIteratorStartSubmitFalse() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        QuizObj qo = qi.nextQuestion();
        QuizObj feedback = qi.submit(qo.answer()+"incorrect");
        assertSame(feedback.type(), QuestionType.FEEDBACK_FAILED);
    }
    @Test
    public void testQuizIteratorStartSubmitTrue() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        QuizObj qo = qi.nextQuestion();
        QuizObj feedback = qi.submit(qo.answer());
        assertSame(feedback.type(), QuestionType.FEEDBACK_PASSED);
    }

    @Test(expected = InputValidationException.class)
    public void testQuizIteratorStartSubmitNull() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        IQuizNavigation qi = sessionManager.startQuiz();
        QuizObj qo = qi.nextQuestion();
        QuizObj feedback = qi.submit(null);
    }

    @Test
    public void testQuizIteratorStartNoQuestions() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException, SQLException {
        String user = "SA";
        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'Example', 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO CHAPTER VALUES(555, 'Example', 555, 'example description');");
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(555);
        sessionManager.setActiveChapter(555);
        IQuizNavigation qi = sessionManager.startQuiz();
        assertFalse(qi.hasNextQuestion());
    }

    @Test
    public void testQuizIteratorStartOneQuestion() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException, SQLException {
        String user = "SA";
        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'Example', 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO CHAPTER VALUES(555, 'Example', 555, 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO QUIZ VALUES(555, 555, 'MULTI_CHOICE', 'prompt', TRUE, 'answer', '1, 2, 3', 'Oops! Thats the wrong answer', 'Great job! Thats the correct answer!')");
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(555);
        sessionManager.setActiveChapter(555);
        IQuizNavigation qi = sessionManager.startQuiz();
        assertTrue(qi.hasNextQuestion());
        assertNotNull(qi.nextQuestion());
        assertFalse(qi.hasNextQuestion());
    }

    @Test
    public void testQuizIteratorStartSubmitFalseAnswer() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException, SQLException {
        String user = "SA";
        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'Example', 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO CHAPTER VALUES(555, 'Example', 555, 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO QUIZ VALUES(555, 555, 'MULTI_CHOICE', 'prompt', FALSE, 'answer', '1, 2, 3', 'Oops! Thats the wrong answer', 'Great job! Thats the correct answer!')");
        SqlTestRunner.executeUpdate("INSERT INTO QUIZ VALUES(556, 555, 'MULTI_CHOICE', 'prompt', TRUE, 'answer', '1, 2, 3', 'Oops! Thats the wrong answer', 'Great job! Thats the correct answer!')");
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(555);
        sessionManager.setActiveChapter(555);
        IQuizNavigation qi = sessionManager.startQuiz();
        qi.nextQuestion();
        assertSame(qi.submit("").type(), QuestionType.MULTI_CHOICE);
    }

    @Test
    public void testQuizIteratorStartSubmitNoAnswer() throws CourseNotFoundException, AccountPermissionException, InputValidationException, DataInaccessibleException, NoItemSelectedException, SQLException {
        String user = "SA";
        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'Example', 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO CHAPTER VALUES(555, 'Example', 555, 'example description');");
        SqlTestRunner.executeUpdate("INSERT INTO QUIZ VALUES(555, 555, 'MULTI_CHOICE', 'prompt', TRUE, '', '1, 2, 3', 'Oops! Thats the wrong answer', 'Great job! Thats the correct answer!')");
        SqlTestRunner.executeUpdate("INSERT INTO QUIZ VALUES(556, 555, 'MULTI_CHOICE', 'prompt', TRUE, 'answer', '1, 2, 3', 'Oops! Thats the wrong answer', 'Great job! Thats the correct answer!')");
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(555);
        sessionManager.setActiveChapter(555);
        IQuizNavigation qi = sessionManager.startQuiz();
        qi.nextQuestion();
        assertSame(qi.submit("").type(), QuestionType.MULTI_CHOICE);
    }
}
