package com.datawarehouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class FileUploadControllerTest extends Mockito{

 

    @Autowired
    private WebApplicationContext webApplicationContext;


	@Spy
	ModelMap model;
	
	
	String fileName = "sample.csv";
	
	File file;
	
    HttpServletRequest request = mock(HttpServletRequest.class);       
    HttpServletResponse response = mock(HttpServletResponse.class);   
    
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource(fileName).getFile());
		System.out.println(file.getAbsolutePath());
	}
	


	@Test
	public void newFileUpload() throws FileNotFoundException, IOException{
		String METHOD_NAME = "newStorage";
		
        MockMultipartFile multipartFile = new MockMultipartFile(fileName, 
				org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(file)));

		
		 MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	        try {
				mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				                .file(multipartFile))
				            .andExpect(MockMvcResultMatchers.status().is(200))
				            		.andReturn();
			} catch (Exception e) {
				e.printStackTrace();
			}		
	                    		
		Assert.assertNotNull(model.get("documents"));
	}
	

	
}
