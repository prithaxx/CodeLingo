package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.CourseObjFactory;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;

public class SessionManager implements ISessionManager {

    //instance fields
    private final IQuizHandler quizHandler;
    private final IAccountHandler accountHandler;
    private final ICourseHandler courseHandler;
    private AccountObj account;
    private CourseObj course;
    private int chapterId;


    public SessionManager(IQuizHandler quizHandler, IAccountHandler accountHandler, ICourseHandler courseHandler) {
        this.quizHandler = quizHandler;
        this.accountHandler = accountHandler;
        this.courseHandler = courseHandler;
    }

    @Override
    public void guestLogin(String user) throws DataInaccessibleException, InputValidationException {
        storeAccount(accountHandler.guestLogin(user));
    }

    @Override
    public void guestLogin(String user, boolean stayLoggedIn) throws DataInaccessibleException, InputValidationException {
        storeAccount(accountHandler.guestLogin(user, stayLoggedIn));
    }

    @Override
    public boolean autoLogin() {
        AccountObj acc = accountHandler.autoLogin();
        if (acc != null) {
            storeAccount(acc);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        account = null;
        course = null;
        chapterId = -1;
        accountHandler.logout();
    }

    @Override
    public AccountObj getActiveAccount() throws AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return account;
    }

    @Override
    public IQuizIterator startQuiz() throws NoItemSelectedException {
        if (course==null || chapterId<0) {
            throw new NoItemSelectedException(Strings.NoCourseSelected);
        }
        return quizHandler.getQuiz(course.id(), chapterId);
    }

    @Override
    public CourseObj getActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        //return course;
        if (this.account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        try {
            course = courseHandler.getActiveCourse(account);
            return course;
        } catch (CourseNotFoundException e) {
            course = CourseObjFactory.getNoneCourse();
            throw e;
        }
    }

    @Override
    public void setActiveCourse(int index) throws CourseNotFoundException, AccountPermissionException {
        accountHandler.setActiveCourse(account, index);
        getActiveCourse();
    }

    @Override
    public List<CourseObj> getCourseList(){
        return courseHandler.getCourseList(account);
    }

    @Override
    public void setActiveChapter(int index) {
        chapterId = index;
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return courseHandler.getActiveCourseChapters(account);
    }

    @Override
    public int calculateProgressPercentage(CourseObj course) throws CourseNotFoundException {
        return courseHandler.calculateProgressPercentage(account, course);
    }

    private void storeAccount(AccountObj acc) {
        this.account = acc;
        try {
            getActiveCourse();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            e.printStackTrace(); //Suppress these error, it's irrelevant on login
        }
    }
}
