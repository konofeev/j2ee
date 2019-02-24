package konofeev.servlet;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

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
            value = "Конфигурация сервлета аннотациями в коде"
        )
    }
)
public class ServletTest extends HttpServlet
{
    String type;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        type = config.getInitParameter("type");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession(true);
        SessionCookieConfig cookie = context.getSessionCookieConfig();
        context.log("Message. Сообщение для логгирования в серлете через контекст сервлета");
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
            if (type != null)
            {
                out.println("<p>Параметр инициализации: " + type + "</p>");
            }

            if (cookie != null)
            {
                out.println("<p>Конфигурация сессии</p>");
                out.println("<ul>");
                out.println("<li>comment: "   + cookie.getComment() + "</li>");
                out.println("<li>domain: "    + cookie.getDomain()  + "</li>");
                out.println("<li>max age: "   + cookie.getMaxAge()  + "</li>");
                out.println("<li>name: "      + cookie.getName()    + "</li>");
                out.println("<li>path: "      + cookie.getPath()    + "</li>");
                out.println("<li>http only: " + cookie.isHttpOnly() + "</li>");
                out.println("<li>secure: "    + cookie.isSecure()   + "</li>");
                out.println("</ul>");
            }
            if (session != null)
            {
                out.println("<p>Параметры сессии</p>");
                out.println("<ul>");
                for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements(); )
                {
                    String attribute = e.nextElement();
                    out.println("<li>" + attribute + ": " + session.getAttribute(attribute) + "</li>");
                }
                out.println("</ul>");
            }
            out.println("</body></html>");
        }
        catch (Exception exception)
        {
            //
        }
    }
}
