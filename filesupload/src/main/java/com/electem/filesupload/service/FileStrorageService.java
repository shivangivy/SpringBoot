/**
 * 
 */
package com.electem.filesupload.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface: FilesStorageService helps us to initialize storage, 
 * save new file, load file, get list of Files’ info, delete all files.
 *
 */
public interface FileStrorageService {
	
	public void initalize();
	
	public void save(MultipartFile file);
	
	public Resource load(String fileName);
	
	public void deleteAll();
	
	public Stream<Path> loadAll();

}
