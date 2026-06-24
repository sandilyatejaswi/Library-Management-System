package dao;

import database.DBConnection;
import model.Book;

import java.sql.*;

public class BookDAO {

    public void addBook(Book book) {

        String sql =
                "INSERT INTO books VALUES(?,?,?,?)";

        try(
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ){

            ps.setInt(1,book.getId());
            ps.setString(2,book.getTitle());
            ps.setString(3,book.getAuthor());
            ps.setBoolean(4,false);

            ps.executeUpdate();

            System.out.println("Book Added");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void displayBooks() {

        String sql =
                "SELECT * FROM books";

        try(
                Connection con =
                        DBConnection.getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ){

            while(rs.next()){

                System.out.println(
                        rs.getInt("id")
                        +" | "
                        +rs.getString("title")
                        +" | "
                        +rs.getString("author")
                        +" | "
                        +rs.getBoolean("issued")
                );
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void issueBook(int id){

        String sql =
                "UPDATE books SET issued=true WHERE id=?";

        try(
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ){

            ps.setInt(1,id);

            int rows =
                    ps.executeUpdate();

            if(rows>0)
                System.out.println("Book Issued");
            else
                System.out.println("Book Not Found");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void returnBook(int id){

        String sql =
                "UPDATE books SET issued=false WHERE id=?";

        try(
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ){

            ps.setInt(1,id);

            int rows =
                    ps.executeUpdate();

            if(rows>0)
                System.out.println("Book Returned");
            else
                System.out.println("Book Not Found");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteBook(int id){

        String sql =
                "DELETE FROM books WHERE id=?";

        try(
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ){

            ps.setInt(1,id);

            int rows =
                    ps.executeUpdate();

            if(rows>0)
                System.out.println("Book Deleted");
            else
                System.out.println("Book Not Found");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}