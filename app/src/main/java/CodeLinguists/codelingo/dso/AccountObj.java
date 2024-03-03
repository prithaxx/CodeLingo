package CodeLinguists.codelingo.dso;

public class AccountObj {
    private final int id;
    private final String name;
    private final boolean isGuest;
    private int activeCourseId;
    private final String username;
    private final String password;

    public AccountObj(int id, String name, boolean isGuest, int activeCourseId, String username, String password) {
        this.id = id;
        this.name = name;
        this.isGuest = isGuest;
        this.activeCourseId = activeCourseId;
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

    public int getActiveCourseId() {
        return activeCourseId;
    }

    public void setActiveCourseId(int activeCourseId) {
        this.activeCourseId = activeCourseId;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
