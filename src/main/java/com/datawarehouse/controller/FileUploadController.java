package com.datawarehouse.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.datawarehouse.service.DealService;

@Controller
public class FileUploadController {
	
	@Autowired
	DealService dealservice;
	
	
	private static final Logger logger = LoggerFactory
	        .getLogger(FileUploadController.class);
	
	private static final String [] FILE_HEADER_MAPPING = {"id","fromCurrency","toCurrency","timestamp","amount"};
	
	private static final String UPLOAD_SUCCESS_STATUS="File uploaded Successfully";
	private static final String UPLOAD_FAIL_STATUS="Error while uploading file.Please check the logs";
	
	 @RequestMapping(value="/", method = RequestMethod.GET)
	    public ModelAndView welcome(ModelMap model) {
		 logger.info("Inside the controller method");
		   ModelAndView mv = new ModelAndView();
	       mv.setViewName("fileupload");
	       return mv;
	    }
	 
	 @RequestMapping(value="/fileupload", method = RequestMethod.POST)
	    public ModelAndView upload(ModelMap model, @RequestParam("targetFile") MultipartFile file,HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("fileupload");
		 String message="";
		 String finalStatus="";
		 String name=file.getOriginalFilename(); 
		 FileReader fileReader = null;
		 CSVParser csvFileParser = null;

		 logger.info("File Name"+name);
		 //checking if the file is empty
		 if (file.isEmpty()) {
			 	message="File is empty";        	
	            model.put("messages", message);
	            logger.info("the file is empty");
	            mv.addObject("message", message);
	            return mv;
	        }
		 if (dealservice.checkFileExist(name)) {
			 	message="File Already Processed";        	
	            model.put("messages", message);
	            logger.info("the file already been processed");
	            mv.addObject("message", message);
	            return mv;
	        }	 	
		 try {		 
			 	byte[] bytes = file.getBytes();
				File dir = new File("csvfiles" + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				logger.info("Creating the file on server");
				File uploadedFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.close();		
				fileReader = new FileReader(uploadedFile);	
				CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
				logger.info("using apache commons CSV parser");
				csvFileParser = new CSVParser(fileReader, csvFileFormat);
				List<CSVRecord> csvRecords = csvFileParser.getRecords();
				String status=dealservice.validateDeals(csvRecords,name);
				csvFileParser.close();
				if("Success".equalsIgnoreCase(status)) {
					finalStatus=UPLOAD_SUCCESS_STATUS;
				}else {
					finalStatus=UPLOAD_FAIL_STATUS;
				}
		 }catch (Exception e) {
			 logger.info("Error"+ e.getMessage());
			 mv.addObject("message",UPLOAD_FAIL_STATUS);
			 return mv;
		}	
		 
		 mv.addObject("message", finalStatus);
		 return mv ;
	
	    }

}
