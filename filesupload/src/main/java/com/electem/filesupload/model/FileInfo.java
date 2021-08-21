/**
 * 
 */
package com.electem.filesupload.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Class
 *
 */
@Entity
@Table(name="Files")
public class FileInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String url;
	private Date date;
	
	/**
	 * Constructor
	 *
	 */
	public FileInfo(String name, String url, Date date) {
		super();
		this.name = name;
		this.url = url;
		this.date = date;
	}
	
	/**
	 * Getter & Setter
	 *
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FileInfo() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
