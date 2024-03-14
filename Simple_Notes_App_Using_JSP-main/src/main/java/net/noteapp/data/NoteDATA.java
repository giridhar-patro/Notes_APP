package net.noteapp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.noteapp.model.Note;

/**
 * AbstractDATA.java This DATA class provides CRUD database operations for the
 * table notes in the database.
 * 
 * @author Muhammad Daffa Ashdaqfillah
 *
 */

public class NoteDATA {
	private String jdbcURL = "jdbc:mysql://localhost:3306/q2_webpro?useSSL=false";
	private String jdbcNotename = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_NOTES_SQL = "INSERT INTO notes" + "  (name, title, text) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_NOTE_BY_ID = "select id,name,title,text from notes where id =?";
	private static final String SELECT_ALL_NOTES = "select * from notes";
	private static final String SELECT_ALL_NOTES_SORTED_ASC = "select * from notes order by name asc";
	private static final String SELECT_ALL_NOTES_SORTED_DESC = "select * from notes order by name desc";
	private static final String SELECT_NOTES_BY_QUERY = "select * from notes where name like ? or title like ? or text like ?";
	private static final String DELETE_NOTES_SQL = "delete from notes where id = ?;";
	private static final String UPDATE_NOTES_SQL = "update notes set name = ?,title= ?, text =? where id = ?;";

	public NoteDATA() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcNotename, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return connection;
	}

	public void insertNote(Note note) throws SQLException {
		System.out.println(INSERT_NOTES_SQL);

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES_SQL)) {
			preparedStatement.setString(1, note.getName());
			preparedStatement.setString(2, note.getTitle());
			preparedStatement.setString(3, note.getText());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Note selectNote(int id) {
		Note note = null;

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				note = new Note(id, name, title, text);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return note;
	}

	public List<Note> selectAllNotes() {


		List<Note> notes = new ArrayList<>();

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				notes.add(new Note(id, name, title, text));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}

	public list<Note> selectAllNotesASC() {
		List<Note> notes = new ArrayList<>();

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES_SORTED_ASC);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				notes.add(new Note(id, name, title, text));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}

	public list<Note> selectAllNotesDESC() {
		List<Note> notes = new ArrayList<>();

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES_SORTED_DESC);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				notes.add(new Note(id, name, title, text));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}

	public List<Note> searchNotes(String query) {
		List<Note> notes = new ArrayList<>();

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_QUERY);) {

			preparedStatement.setString(1, "%" + query + "%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				notes.add(new Note(id, name, title, text));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}

	public boolean deleteNote(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateNote(Note note) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_NOTES_SQL);) {
			statement.setString(1, note.getName());
			statement.setString(2, note.getTitle());
			statement.setString(3, note.getText());
			statement.setInt(4, note.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
