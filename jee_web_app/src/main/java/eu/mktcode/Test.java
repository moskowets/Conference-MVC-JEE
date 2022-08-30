package eu.mktcode;

import eu.mktcode.app.datasource.database.ConnectionPool;
import eu.mktcode.app.datasource.database.PooledConnection;
import eu.mktcode.model.dao.Dao;
import eu.mktcode.model.dao.UserDao;
import eu.mktcode.model.entities.User;
import eu.mktcode.model.entities.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/hi")
public class Test extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Dao<User> dao = new UserDao();
/*        User user = new User();
        user.setId(10);
        user.setRole(Role.MODERATOR);
        user.setEmail("colt.pcw@gmail.com");
        user.setPassword("1234");
        user.setLanguage(1);
        dao.update(user);*/
        List<User> lst = dao.getAll();
        User ten = dao.get(10).orElse(null);


        try (PooledConnection con = ConnectionPool.get().getPooledConnection()) {
            out.println("<html><body>");
            out.println("<h1>" + con.getConnection().getCatalog() + "</h1>");
            out.println("<h1> UserID: " + ten.getId() + "</h1>");
            out.println("</body></html>");
        } catch (SQLException e) {
            out.println("<html><body>");
            out.println("<h1>" + "Error " + e.getMessage() + "</h1>");
            out.println("</body></html>");
       }

    }

    @Override
    public void destroy() {
        ConnectionPool.get().closeAll();
    }
}