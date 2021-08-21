/**
 * 
 */
package com.electem.filesupload.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.electem.filesupload.model.FileInfo;
import com.electem.filesupload.repository.FileStorageRepository;

/**
 * Implementation of File Storage Service
 *
 */
@Service
public class FileStorageServiceImpl implements FileStrorageService {
	@Autowired
	private FileStorageRepository fileStorageRepository;
	
	private final Path root = Paths.get("uploads");

	@Override
	public void initalize() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Colud not initialize folder for upload");
		}
		
	}
	
	/**
	 * save files
	 *
	 */
	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		
			//fileStorageRepository.saveAll(file);
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file" + e.getMessage());
		}
		
	}
	
	@Override
	public Resource load(String fileName) {
		
		
		try {
			Path file = root.resolve(fileName);
			Resource resourcer = new UrlResource(file.toUri());
			
			if(resourcer.exists() || resourcer.isReadable()){
				return resourcer;
			} else {
				throw new RuntimeException("Could not read the file");
			}
			
		} catch (MalformedURLException e) {
			
			throw new RuntimeException("Invalid URL" + e.getMessage());
		}
		
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
		
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load files");
		}
		
	}
	
	/**
	 * To convert timezone to IST
	 * @throws ParseException 
	 *
	 */
	public List<FileInfo> getListFiles() throws ParseException{
		List<FileInfo> files=fileStorageRepository.findAll();
		for (FileInfo fileInfo : files) {
			Date newdate=fileInfo.getDate();
		
			Calendar cal=new GregorianCalendar();
			cal.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			cal.setTime(newdate);
			Date convertedDate=cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
			String dateconverted=formatter.format(convertedDate);
			Date d=formatter.parse(dateconverted);
			fileInfo.setDate(d);
			System.out.println(d);
		}
		return files;
	}
	
	/**
	 * To save date in different format
	 *
	 */
	public Date getDate(Date date){
		Calendar cal=new GregorianCalendar();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		cal.setTime(date);
		Date convertedDate=cal.getTime();
		return convertedDate;  
	}

}
