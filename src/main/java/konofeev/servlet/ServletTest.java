package konofeev.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.io.IOException;

@WebServlet("/servlet")
public class ServletTest extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try (PrintWriter out = response.getWriter())
        {
            out.println("<html><head>");
            out.println("<title>ServletTest</title>");
            out.println("</head><body>");
            out.println("<h1>My test servlet</h1>");
            out.println("</bory></html>");
        }
        catch (IOException exception)
        {
            //
        }
        finally
        {
            //
        }
    }

}
