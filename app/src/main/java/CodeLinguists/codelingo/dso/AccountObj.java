package CodeLinguists.codelingo.dso;

public class AccountObj {
    private final int id;
    private final String name;
    private final boolean isGuest;
    private CourseObj activeCourse;

    public AccountObj(int id, String name, boolean isGuest, CourseObj activeCourse) {
        this.id = id;
        this.name = name;
        this.isGuest = isGuest;
        this.activeCourse = activeCourse;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGuestAccount() {
        return isGuest;
    }

    public CourseObj getActiveCourse() {
        return activeCourse;
    }

    public void setActiveCourse(CourseObj activeCourse) {
        this.activeCourse = activeCourse;
    }
}
