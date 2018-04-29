import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Servlet0 extends HttpServlet{
	protected void service(HttpServletRequest req, httpservletResponse resp)throws IOException{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<HTML>\n");
		buffer.append("<HEAD>\n");
		buffer.append("<TITTLE>Primer Servlet</Tittle>\n");
		buffer.append("</HEAD>\n");
		buffer.append("<H1>Hola Que tal!</H1>\n");
		buffer.append("</HTML>\n");
		resp.setContetType("text/html");
		resp.setContentLength(buffer.length());
		resp.getOutputStream().print(buffer.toString());
	}
}