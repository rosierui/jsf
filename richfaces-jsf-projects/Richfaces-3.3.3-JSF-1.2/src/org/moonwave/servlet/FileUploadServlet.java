package org.moonwave.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

/**
 * Servlet implementation class FileUploadServlet
 * 
 * Reference: 
 * 	http://stackoverflow.com/questions/14932225/multiple-file-upload-using-single-servlet-request (***)
 * 
 * 	http://javaevangelist.blogspot.com/2013/09/apache-commons-file-upload-servlet.html
 * 
 * Client side:
 * 	fileUpload.jsp
 *  
 *  Working directory
 *  /home/jonathan/project/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Richfaces-3.3.3-JSF-1.2/uploadedFiles
 *  
 *  getServletContext().getRealPath("/") returns
 *  1) /home/jonathan/project/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Richfaces-3.3.3-JSF-1.2
 * 		When do in-place deployment, i.e. Location: [workspace metadata]
 *  2) /home/jonathan/local/srv/tomcat-7.0.50/webapps/Richfaces-3.3.3-JSF-1.2/
 * 		When deployed as Richfaces-3.3.3-JSF-1.2.war under /home/jonathan/local/srv/tomcat-7.0.50/webapps/
 *
 * @author jonathan
 * Created 1/23/2014 11:50 AM
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
		}
		else{
	      FileItemFactory factory = new DiskFileItemFactory();
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      List items = null;
	      try {
	          	items = upload.parseRequest(request);
	          } catch (FileUploadException e) {
	            e.printStackTrace();
          }
	      Iterator itr = items.iterator();
	      while (itr.hasNext()) {
	           FileItem item = (FileItem) itr.next();
	           if (item.isFormField()) {
                   // get the name of the field
                   String fieldName = item.getFieldName();

                   // if it is name, we can set it in request to thank the user
                   if (fieldName.equals("name")) {
                       out.print("Thank You: " + item.getString());
                   }
	           } else {
		           try {
		              String itemName = item.getName();
		              if (!itemName.isEmpty()) { // there are three file upload controls in fileupload.jsp, if only one 
		            	  // is use, other two will post blank filename back
		            	  String uploadFolder = getServletContext().getRealPath("/") + "uploadedFiles";
		            	  File folder = new File(uploadFolder);
		            	  if (!folder.exists()) {
		            		  folder.mkdir();
		            	  }
		            	  String filename = uploadFolder + "/" + itemName;
			              File savedFile = new File(filename);
			              item.write(savedFile);
			              out.println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>" + getServletContext().getRealPath("/")+"uploadedFiles"+"\\"+itemName+"</td></tr>");
		              }
	              } catch (Exception e) {
	                   e.printStackTrace();
	              }
	           }
	      	}	
		}
	}
}

