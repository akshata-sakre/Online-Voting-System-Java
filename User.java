package VotingSystem;

public class User {
    private int userId;
    private String name;
    private String password;
    private boolean hasVoted;

    // Constructor
    public User(int userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.hasVoted = false;
    }

    // Getters & Setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}