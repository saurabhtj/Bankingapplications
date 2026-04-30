package banking;

import java.sql.*;

public class Database {
    Connection con;

    public Database() {
        try {
           
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Saurabh\\OneDrive\\Documents\\code board\\java\\Banking.db");

            
            Statement st = con.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS banking_table (" +
                       "name TEXT, address TEXT, city TEXT, pincode INTEGER, initial_deposit INTEGER)");

            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void createAccount(String name, String addr, String city, int pin, int dep) {
        String sql = "INSERT INTO banking_table VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setString(3, city);
            ps.setInt(4, pin);
            ps.setInt(5, dep);
            ps.executeUpdate();
            System.out.println("Account Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void listAccounts() {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM banking_table")) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                        rs.getString("address") + " | " +
                        rs.getString("city") + " | " +
                        rs.getInt("pincode") + " | Balance: " +
                        rs.getInt("initial_deposit"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void updateDeposit(String name, int amt) {
        String sql = "UPDATE banking_table SET initial_deposit=? WHERE name=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, amt);
            ps.setString(2, name);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Balance Updated");
            else
                System.out.println("Account Not Found");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void closeAccount(String name) {
        String sql = "DELETE FROM banking_table WHERE name=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Account Closed");
            else
                System.out.println("Account Not Found");

        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}