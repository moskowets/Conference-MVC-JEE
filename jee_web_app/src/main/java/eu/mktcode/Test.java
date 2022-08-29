package eu.mktcode;

import eu.mktcode.app.datasource.database.ConnectionPool;
import eu.mktcode.app.datasource.database.PooledConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/hi")
public class Test extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try (PooledConnection con = ConnectionPool.get().getConnection()) {
            out.println("<html><body>");
            out.println("<h1>" + con.getConnection().getCatalog() + "</h1>");
            out.println("</body></html>");
        } catch (SQLException e) {
            out.println("<html><body>");
            out.println("<h1>" + "Error " + e.getMessage() + "</h1>");
            out.println("</body></html>");
       }

    }
}