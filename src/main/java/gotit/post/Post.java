package gotit.post;

import java.sql.Date;


public class Post {
	private long post_id;
	private String title;
	private String content;
	private String writer;
	private long views;
	private long likes;
	private long bookmarks;
	private Date created_at;
	
	public Post() {};
	
	public Post(long post_id, String title, String content, String writer, long views, long likes, long bookmarks, Date created_at) {
		//super();
		this.post_id = post_id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.views = views;
		this.bookmarks = bookmarks;
		this.created_at = created_at;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(long bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
}