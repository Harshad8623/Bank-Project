package pk;
import java.sql.*;
import java.util.*;


public class BankSystem {

    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        
        do {
            System.out.println();
            System.out.println(" _Bank Management System_ ");
            System.out.println("1. create account");
            System.out.println("2. deposit money");
            System.out.println("3. withdraw money");
            System.out.println("4. view all accounts");
            System.out.println("5. exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            
            switch (choice) 
            {
                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    viewAllAccounts();
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank System !!!!");
                    break;

                default:
                    System.out.println("\t Invalid choice! \nPlease try again.");
            }

        }while (choice != 5);
    }

    
    static void createAccount() {
        Connection con = null;

        try {
            con = DatabaseConnection.GETConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();

            String query = "INSERT INTO accounts (account_no, name, balance) VALUES (?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, accNo);
            pst.setString(2, name);
            pst.setDouble(3, balance);

            pst.executeUpdate();
            System.out.println("Account Created Successfully !!!!");
        }
        catch (Exception e) 
        {
            System.out.println("Error while creating account: " + e);
        }
        finally 
        {
            try 
            {
                if (con != null)
                    con.close();  
            } 
            catch (Exception ex) 
            {
                System.out.println("Error closing connection: " + ex);
            }
        }
    }


    
    static void depositMoney() 
    {
        Connection con = null;

        try 
        {
            
            con = DatabaseConnection.GETConnection();  

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            System.out.print("Enter Amount to Deposit: ");
            double amount = sc.nextDouble();

            
             String query = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setDouble(1, amount);
            pst.setInt(2, accNo);

            int rows = pst.executeUpdate();

            if (rows > 0) 
            {
                System.out.println("Amount Deposited Successfully !!!!");
            } 
            else 
            {
                System.out.println("Account Not Found !!!!!!");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error while depositing: " + e);
        }
        finally 
        {
            try 
            {
                if (con != null)
                {
                    con.close();
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error closing connection: " + e);
            }
        }
    }


    
    static void withdrawMoney() 
    {
        Connection con = null;

        try 
        {
           
            con = DatabaseConnection.GETConnection();

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            System.out.print("Enter Amount to Withdraw: ");
            double amount = sc.nextDouble();

           
            String checkQuery = "SELECT balance FROM accounts WHERE account_no = ?";
            PreparedStatement pst = con.prepareStatement(checkQuery);
            pst.setInt(1, accNo);

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                double currentBalance = rs.getDouble("balance");

                if (currentBalance >= amount) 
                {
                    
                    String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE account_no = ?";
                    PreparedStatement pst2 = con.prepareStatement(updateQuery);

                    pst2.setDouble(1, amount);
                    pst2.setInt(2, accNo);

                    pst2.executeUpdate();

                    System.out.println("Withdrawal Successful !");
                } 
                else 
                {
                    System.out.println("Insufficient Balance !");
                }
            } 
            else 
            {
                System.out.println("Account Not Found !!!!");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error while withdrawing: " + e);
        }
        finally 
        {
            try 
            {
                if (con != null)
                {
                    con.close();
                }
            } 
            catch (Exception eg) 
            {
                System.out.println("Error closing connection: " + eg);
            }
        }
    }


    static void viewAllAccounts() 
    {
        Connection con = null;

        try 
        {
            
            con = DatabaseConnection.GETConnection();

            String query = "SELECT * FROM accounts";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println();
            System.out.println("All Accounts List");
            System.out.println("Acc No     Name        Balance");

            
            while (rs.next()) 
            {
                int accNo = rs.getInt("account_no");
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");

                System.out.println(accNo + "        " + name + "        " + balance);
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error while fetching accounts: " + e);
        }
        finally 
        {
            try 
            {
                if (con != null)
                {
                    con.close();
                }
            } 
            catch (Exception ex) 
            {
                System.out.println("Error closing connection: " + ex);
            }
        }
    }

}
