/**
 * 
 */
package com.electem.filesupload.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.electem.filesupload.model.FileInfo;
import com.electem.filesupload.repository.FileStorageRepository;
import com.electem.filesupload.service.FileStorageServiceImpl;
import com.electem.filesupload.service.FileStrorageService;
import com.electem.fileupload.message.ResponseMessage;

/**
 * Controller
 *
 */
@RestController
public class FilesController {
	@Autowired
	private FileStorageServiceImpl FileStorageServiceImpl;
	@Autowired
	private FileStrorageService fileStrorageService;
	@Autowired
	private FileStorageRepository fileStorageRepository;
	//private FileInfo fileInfo;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam String date) throws ParseException{
		String message="";
		String fileName = file.getOriginalFilename();
		String sDate1= date;  
		Date date1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate1);  
		String url = MvcUriComponentsBuilder
		          .fromMethodName(FilesController.class, "getFile",  file.getOriginalFilename()).build().toString();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setName(fileName);
		fileInfo.setUrl(url);
		fileInfo.setDate(date1);
		//fileInfo.setUrl(file.get);
		try{
		fileStorageRepository.save(fileInfo);
		fileStrorageService.save(file);
		
		message="File uploaded successfully"+file.getOriginalFilename();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception e){
			message="File could not upload"+ file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
		
 
	
	@GetMapping("/files")
	public List<FileInfo> getListOfFiles(){
		try {
			return FileStorageServiceImpl.getListFiles();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
		
	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = fileStrorageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
}