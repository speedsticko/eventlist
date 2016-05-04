package eventlist.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import eventlist.etl.EventRecord;
import eventlist.etl.JsonStreamLoader;

//import eventlist.etl.EventRecord;

@WebServlet("/upload")
@MultipartConfig
public class UploadController extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /* Make this threadsafe */
	    Part filePart = request.getPart("file");
	    InputStream fileContent = filePart.getInputStream();
	    JsonStreamLoader loader = new JsonStreamLoader();
	    List<EventRecord> list = loader.Load(fileContent);
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("List size:");
	    response.getWriter().write(Integer.toString(list.size()));
	    response.getWriter().write("- content length:");
	    response.getWriter().write(Integer.toString(request.getContentLength()));
	}
	
}
