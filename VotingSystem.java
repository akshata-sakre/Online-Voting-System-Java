package VotingSystem;

import java.sql.*;
import java.util.*;

public class VotingSystem {

    private List<User> users = new ArrayList<>();
    private List<Candidate> candidates = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private int userCounter = 1;
    private int candidateCounter = 1;

    //  User  Register
    public void registerUser() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String query =
                "INSERT INTO users(username, password, hasVoted) VALUES(?,?,false)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            System.out.println("✅ User registered successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // user menu
    public void userMenu() {
        while (true) {
            System.out.println("\n==== USER MENU ====");
            System.out.println("1. Register");
            System.out.println("2. Login & Vote");
            System.out.println("3. Back");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("❌ Enter number only!");
                sc.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginAndVote();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    // Login + Vote
    public void loginAndVote() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            String query = 
                "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

             // check if already voted 
             if (rs.getBoolean("hasVoted")) {

                    System.out.println("❌ Already voted!");
                    return;
                }

                // show candidates
                showCandidates();

                System.out.print("Select your candidate: ");
                int choice = sc.nextInt();
                sc.nextLine();

                // vote update
                String voteQuery =
                    "UPDATE candidates SET voteCount = voteCount + 1 WHERE candidateId=?";

                PreparedStatement ps2 = con.prepareStatement(voteQuery);

                ps2.setInt(1, choice);

                ps2.executeUpdate();

                // mark user as voted
                String updateUser =
                    "UPDATE users SET hasVoted=true WHERE username=?";

                PreparedStatement ps3 = con.prepareStatement(updateUser);

                ps3.setString(1, username);

                ps3.executeUpdate();

                System.out.println("✅ Vote cast successfully!");

            } else {

                System.out.println("❌ Invalid login!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
//    ------------------------------------------------------------------
    
    // admin login
    public boolean adminLogin() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Admin Username: ");
            String user = sc.next();

            System.out.print("Enter Admin Password: ");
            String pass = sc.next();

            String query = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Admin login successful!");
                return true;
            } else {
                System.out.println("❌ Invalid admin credentials!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            return false;
        }
    }
    
    // admin menu
    public void adminMenu() {
        if (!adminLogin()) return;

        while (true) {
            System.out.println("\n==== ADMIN MENU ====");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Results");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    showResults();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    // Add Candidate (Admin)
    public void addCandidate() {

        try {

            Connection con = DBConnection.getConnection();

            sc.nextLine();

            System.out.print("Enter Candidate Name: ");
            String cname = sc.nextLine();

            String query =
                "INSERT INTO candidates(name, voteCount) VALUES(?,0)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, cname);

            ps.executeUpdate();

            System.out.println("✅ Candidate added successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    
    // Show Candidates
    public void showCandidates() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM candidates";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n===== CANDIDATES =====");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("candidateId") + ". "
                    + rs.getString("name")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    
    
    // Show Results
    public void showResults() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM candidates";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Results ---");

            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + " - Votes: " + rs.getInt("voteCount")
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}