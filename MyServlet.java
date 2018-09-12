

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	   static String user = "newremoteuser";
	   static String password = "password";
	   static Connection connection = null;
	   static String url = "jdbc:mysql://ec2-18-221-222-212.us-east-2.compute.amazonaws.com:3306/myDB";
       
    public MyServlet() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			response.getWriter().println("To-Do List");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}		
					
		
		connection = null;
		try 
		{
	        connection = DriverManager.getConnection(url, user, password);
	    } 
		catch (SQLException e) 
		{
	         response.getWriter().println("Connection Failed! Check output console");
	         e.printStackTrace();
	         return;
	      }
		
		if (connection != null) {
	         //response.getWriter().println("You made it, take control your database now!<br>");
		}
		/*
		//String selectSQL = "SELECT * FROM myTable WHERE ITEM LIKE ?";
		//String theUserName = "*%";
		try {
			//PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, theUserName);
			
			Statement statement = connection.createStatement();
	        ResultSet rs = statement.executeQuery("SELECT * FROM stTable");
	        
	        while (rs.next()) {
	            String item = rs.getString("ITEM");
	            String importance = rs.getString("IMPORTANCE");
	            String date = rs.getString("DUEDATE");
	            response.getWriter().append("ITEM: " + item + ", ");
	            response.getWriter().append("IMPORTANCE: " + importance + ", ");
	            response.getWriter().append("DUE DATE " + date + ", ");
	            
	            
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String item = request.getParameter("Item");
		
		//String htmlResponse = "<html>" + "<h2>Your username is: " + item + "<br/>" + "</html>";
        //response.getWriter().println(htmlResponse);
		
	*/	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String item = request.getParameter("Item");
		String date = request.getParameter("Due Date");
		String importance = request.getParameter("Importance");
		//String importance = "low";
		String insert = request.getParameter("insert");
		String delete = request.getParameter("delete");
		String view = request.getParameter("view");
		String deleteItem = request.getParameter("DeleteItem");
		if (view != null)
		{
		try {
			//PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, theUserName);
			Statement statement = connection.createStatement();
	        ResultSet rs = statement.executeQuery("SELECT * FROM stTable");
	        PrintWriter writer = response.getWriter();
	        while (rs.next()) {
	            String ite = rs.getString("ITEM");
	            String importanc = rs.getString("IMPORTANCE");
	            String dat = rs.getString("DUEDATE");
	            String htmlResponse = "ITEM: " + ite + " DUE DATE: " + dat + " IMPORTANCE: "+ importanc;
	            writer.println(htmlResponse);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
        
		 
        //String htmlResponse = "<html>" + "<h2> ITEM:" + item + " Due Date: " + date + "Importance "+ importance + "<br/>" + "</html>";
        //writer.println(htmlResponse);
        if (insert != null) {
        String query = "INSERT INTO stTable(ITEM, DUEDATE, IMPORTANCE) VALUES (?,?,?)";
        
        PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);
        statement.setString(1, item);
        statement.setString(2, date);
        statement.setString(3, importance);
        statement.executeUpdate();
        
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, theUserName);
			Statement state = connection.createStatement();
	        ResultSet rs = state.executeQuery("SELECT * FROM stTable");
	        PrintWriter writer = response.getWriter();
	        while (rs.next()) {
	            String ite = rs.getString("ITEM");
	            String importanc = rs.getString("IMPORTANCE");
	            String dat = rs.getString("DUEDATE");
	            String htmlResponse = "ITEM: " + ite + " Due Date: " + dat + " Importance "+ importanc;
	            writer.println(htmlResponse);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        }
        
        if (delete != null)
        {
        	String query = "DELETE FROM stTable WHERE item LIKE ?";
        	PreparedStatement statement = null;
    	
    		try {
				statement = connection.prepareStatement(query);
				statement.setString(1, deleteItem);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
            
    		try {
    			//PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
    			//preparedStatement.setString(1, theUserName);
    			Statement state = connection.createStatement();
    	        ResultSet rs = state.executeQuery("SELECT * FROM stTable");
    	        PrintWriter writer = response.getWriter();
    	        while (rs.next()) {
    	            String ite = rs.getString("ITEM");
    	            String importanc = rs.getString("IMPORTANCE");
    	            String dat = rs.getString("DUEDATE");
    	            String htmlResponse = "ITEM: " + ite + " Due Date: " + dat + " Importance "+ importanc;
    	            writer.println(htmlResponse);
    	         }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        }
    		
        
      
	}

}
