package CodeLinguists.codelingo.dso;

public class AccountObj {
    private final int id;
    private final String name;
    private final boolean isGuest;
    private CourseObj activeCourse;  
    private final String username;
    private final String password;

    public AccountObj(int id, String name, boolean isGuest, CourseObj activeCourse, String username, String password) {
        this.id = id;
        this.name = name;
        this.isGuest = isGuest;
        this.activeCourse = activeCourse;
        this.username = username;
        this.password = password;
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

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
