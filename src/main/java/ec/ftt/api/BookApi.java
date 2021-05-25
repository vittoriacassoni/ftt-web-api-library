package ec.ftt.api;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.ftt.dao.BookDao;
import ec.ftt.model.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/emprestimoLivros")
public class BookApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String bookId = request.getParameter("bookId");
             
	    if(bookId != null) {
	    	long id = Long.valueOf(bookId);
	    	
	    	BookDao bookDAO = new BookDao();
	    	
	        Book book = bookDAO.getBookById(id);
                System.out.println(book);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(book));
	    	
	    } else {
	    	BookDao bookDAO = new BookDao();
	    	
	    	List<Book> books = null;
                try {
                    books = bookDAO.getAllBook();
                } catch (SQLException ex) {
                    Logger.getLogger(BookApi.class.getName()).log(Level.SEVERE, null, ex);
                }
	        System.out.println(books);
	    	Gson gson = new Gson();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
	    	response.getWriter().append(gson.toJson(books));
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("oi");
		Book a = new Book(
				request.getParameter("book-id"),
				request.getParameter("book-title"),
				request.getParameter("book-author")
				);
		
		BookDao bookDao = new BookDao();
                
                if(bookDao.getBookById(a) != null){
                    bookDao.updateBook(a);
                }
                else{
                   bookDao.addBook(a);
                }
		
		
		System.out.println(a);
		
		//response.getWriter().append(a.toString());
                response.sendRedirect("index.html");
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Book a = new Book(
		request.getParameter("book-id"),
		request.getParameter("book-title"),
		request.getParameter("book-author")
            );
            BookDao bookDAO = new BookDao();
		
            bookDAO.updateBook(a);
		
            System.out.println(a);
		
            response.sendRedirect("index.html");	
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setStatus(418); //200 - OK - Padrão (Default)

            if (request.getParameter("bookId") == null)
                response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
            else {
                Long bookId = Long.valueOf(request.getParameter("bookId"));

                BookDao bookDAO = new BookDao();
		
                bookDAO.deleteBook(bookId);
		
                response.getWriter().append(request.getParameter("bookId") + " User removido");
            }
	}

}
