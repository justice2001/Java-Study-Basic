package top.mrl.server.servlet01;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request,Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<title>Response</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("Register Done!");
        response.print("</body>");
        response.print("</html>");
    }
}
