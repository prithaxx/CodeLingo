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
        course = CourseObjFactory.getNoneCourse();
        chapterId = -1;
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
    public void setChapterComplete() throws CourseNotFoundException, AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        courseHandler.setChapterComplete(this.chapterId, this.account);
    }

    @Override
    public AccountObj getActiveAccount() throws AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return account;
    }

    @Override
    public IQuizNavigation startQuiz() throws NoItemSelectedException {
        if (course==null) {
            throw new NoItemSelectedException(Strings.NoCourseSelected);
        }
        if (chapterId<0) {
            throw new NoItemSelectedException(Strings.NoChapterSelected);
        }
        return quizHandler.getQuiz(course.getId(), chapterId);
    }

    @Override
    public CourseObj getActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        if (this.account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        try {
            this.course = courseHandler.getActiveCourse(account);
            return this.course;
        } catch (CourseNotFoundException e) {
            course = CourseObjFactory.getNoneCourse();
            throw e;
        }
    }

    @Override
    public void setActiveCourse(int courseId) throws CourseNotFoundException, AccountPermissionException, InputValidationException {
        if (this.account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        accountHandler.setActiveCourse(account, courseId);
        getActiveCourse(); //update course variable
    }

    @Override
    public List<CourseObj> getCourseList() throws AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return courseHandler.getCourseList(account);
    }

    @Override
    public void setActiveChapter(int chapterId) throws InputValidationException, AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        if (chapterId < 0) {
            throw new InputValidationException(Strings.ChapterIdPositive);
        }
        this.chapterId = chapterId;
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return courseHandler.getActiveCourseChapters(account);
    }

    @Override
    public int calculateProgressPercentage() throws CourseNotFoundException, AccountPermissionException {
        if (account == null) {
            throw new AccountPermissionException(Strings.NotSignedIn);
        }
        return courseHandler.calculateProgressPercentage(account);
    }

    private void storeAccount(AccountObj acc) {
        this.account = acc;
        try {
            getActiveCourse();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            e.printStackTrace(); //Suppress these errors, getActiveCourse is optional, and permitted to fail
        }
    }
}
