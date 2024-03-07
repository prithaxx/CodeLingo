package codelinguists.codelingo.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.QuizIterator;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;

public class QuizIteratorTest {
    private QuizIterator quizIterator;

    @Before
    public void setUp() {
        QuizDataStub quizDataStub = new QuizDataStub();
        this.quizIterator = new QuizIterator(quizDataStub.getQuizList());
    }
    @Test
    public void testStartQuiz() {
        QuizObj firstQuiz = quizIterator.startQuiz();
        assertNotNull(firstQuiz);
    }
}
