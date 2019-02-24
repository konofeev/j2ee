package konofeev.servlet;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Тестовый сервлет
 */
@WebServlet
(
    urlPatterns = 
    {
        "/servlet",
        "/test"
    },
    initParams = 
    {
        @WebInitParam
        (
            name = "type",
            value = "cheking"
        )
    }
)
public class ServletTest extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        (
            PrintWriter out = new PrintWriter
            (
                new OutputStreamWriter
                (
                    response.getOutputStream(),
                    StandardCharsets.UTF_8
                ),
                true
            )
        )
        {
            response.setCharacterEncoding("utf8");
            response.setContentType("text/html; charset=utf8");
            out.println("<html><head>");
            out.println("<title>ServletTest</title>");
            out.println("</head><body>");
            out.println("<h2>Тестовый сервлет. Используется кодировка UTF8</h2>");
            out.println("<p>Параметры запроса</p>");
            out.println("<ul>");
            for (Map.Entry<String, String[]> entry: request.getParameterMap().entrySet())
            {
                out.println("<li> " + entry.getKey() + ": " + entry.getValue()[0] + "</li>");
            }
            out.println("</ul>");
            out.println("</bory></html>");
        }
        catch (Exception exception)
        {
            //
        }
    }
}
