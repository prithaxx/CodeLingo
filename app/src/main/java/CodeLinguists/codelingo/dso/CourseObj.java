package CodeLinguists.codelingo.dso;
public class CourseObj {
    private final int id;
    private final String name;
    private final String description;
    private boolean isStarted;
    private boolean isCompleted;

    public CourseObj(int id, String name, String description, boolean isStarted, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isStarted = isStarted;
        this.isCompleted = isCompleted;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public String getDescription() {
        return description;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}