package com.electem.filesupload;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.electem.filesupload.service.FileStorageServiceImpl;
import com.electem.filesupload.service.FileStrorageService;

@SpringBootApplication
public class FilesuploadApplication {
	
	@Resource
	FileStorageServiceImpl storageService;

	public static void main(String[] args) {
		SpringApplication.run(FilesuploadApplication.class, args);
	}
		 public void run(String... arg) throws Exception {
		    //storageService.deleteAll();
		    storageService.initalize();
		 
		
	}

}
