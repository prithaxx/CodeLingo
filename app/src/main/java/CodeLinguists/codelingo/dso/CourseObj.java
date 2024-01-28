package CodeLinguists.codelingo.dso;

public class CourseObj {
    private final int id;
    private final String name;
    private final String description;
    private final boolean isStarted;
    private final boolean isCompleted;

    public CourseObj(int id, String name, String description, boolean isStarted, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isStarted = isStarted;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
