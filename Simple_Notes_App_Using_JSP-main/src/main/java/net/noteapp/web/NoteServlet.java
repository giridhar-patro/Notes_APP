package net.noteapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.noteapp.data.NoteDATA;
import net.noteapp.model.Note;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the note.
 * @title Muhammad Daffa Ashdaqfillah
 */

@WebServlet("/")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDATA noteDATA;
	
	public void init() {
		noteDATA = new NoteDATA();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertNote(request, response);
				break;
			case "/delete":
				deleteNote(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateNote(request, response);
				break;
			case "/asc":
				listNoteASC(request, response);
				break;
			case "/desc":
				listNoteDESC(request, response);
				break;
			case "/search":
				searchNote(request, response);
				break;
			default:
				listNote(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Note> listNote = noteDATA.selectAllNotes();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Note existingNote = noteDATA.selectNote(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
		request.setAttribute("note", existingNote);
		dispatcher.forward(request, response);

	}

	private void insertNote(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		Note newNote = new Note(name, title, text);
		noteDATA.insertNote(newNote);
		response.sendRedirect("list");
	}

	private void updateNote(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String text = request.getParameter("text");

		Note book = new Note(id, name, title, text);
		noteDATA.updateNote(book);
		response.sendRedirect("list");
	}

	private void deleteNote(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		noteDATA.deleteNote(id);
		response.sendRedirect("list");

	}

	private void listNoteASC(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Note> listNote = noteDATA.selectAllNotesASC();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}

	private void listNoteDESC(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Note> listNote = noteDATA.selectAllNotesDESC();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}

	private void searchNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		List<Note> listNote = noteDATA.searchNotes(name);
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}
}
