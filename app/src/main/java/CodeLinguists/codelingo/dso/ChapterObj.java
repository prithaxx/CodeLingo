package CodeLinguists.codelingo.dso;

public record ChapterObj( int id,
                        String name,
                        int courseId,
                        String description,
                        boolean isUnlocked,
                        boolean isCompleted) {
    @Override
    public boolean isCompleted() {
        return isCompleted;
    }
}
