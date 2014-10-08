package com.jevoncode.jcs.basicwebexample;

import java.io.Serializable;
import java.util.Date;

public class BookVObj implements Serializable {
	public int bookId = 0;
	public String title;
	public String author;
	public String ISBN;
	public String price;
	public Date publishDate;

	public BookVObj() {
	}
}