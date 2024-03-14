package net.noteapp.model;

/**
 * Note.java
 * This is a model class represents a Note entity
 * @author Muhammad Daffa Ashdaqfillah
 *
 */
public class Note {
	protected int id;
	protected String name;
	protected String title;
	protected String text;
	
	public Note() {
	}
	
	public Note(String name, String title, String text) {
		super();
		this.name = name;
		this.title = title;
		this.text = text;
	}

	public Note(int id, String name, String title, String text) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.text = text;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
