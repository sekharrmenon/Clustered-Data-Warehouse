package com.datawarehouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.datawarehouse.dto.DealDTO;


@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/view-resolver-servlet.xml" })
public class FileUploadControllerTest extends AbstractTestNGSpringContextTests{

 

    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@InjectMocks
	FileUploadController fileUploadController;


	@Spy
	ModelAndView model;
	
	@Spy
	List<DealDTO> validDeals = new ArrayList<DealDTO>();
	
	@Spy
	List<DealDTO> inValidDeals = new ArrayList<>();
	
	
	String fileName = "test1.csv";
	
	File file;
	
	MultipartFile multipartFile;
	
//    HttpServletRequest request = mock(HttpServletRequest.class);       
//    HttpServletResponse response = mock(HttpServletResponse.class);   
	
    

	@BeforeClass
	public void setUp() throws IOException{
		 
		MockitoAnnotations.initMocks(this);
		ClassLoader classLoader = getClass().getClassLoader();
		file =  new File(classLoader.getResource(fileName).getFile());
//		FileInputStream input = new FileInputStream(file);
//		 multipartFile = new MockMultipartFile("file",
//	            file.getName(), "text/plain", IOUtils.toByteArray(input));
//		 List<CSVRecord> allrecord=fileUploadController.getCsvRecords(multipartFile,file.getName());
//		 for(CSVRecord record : allrecord) {			
//				DealDTO deal = new DealDTO();
//				deal.setId(new Integer(record.get(DEAL_ID)));
//				deal.setToCurrency(record.get(TO_CURRENCY));
//				deal.setFromCurrency(record.get(FROM_CURRENCY));
//				
//				try {
//					if(StringUtils.isEmpty(record.get(DEAL_TIMESTAMP))) {
//						deal.setDealDate(null);
//					}else {
//						deal.setDealDate(dateFormatter.parse(record.get(DEAL_TIMESTAMP)));
//					}			
//				} catch (ParseException e) {
//					
//				}
//				try {
//					deal.setAmount(new BigInteger(record.get(DEAL_AMOUNT)));	
//					deal.setFileName(fileName);
//				}catch (NumberFormatException e) {
//					
//				}
//						
//				if(StringUtils.isEmpty(deal.getFromCurrency()) ||
//						StringUtils.isEmpty(deal.getToCurrency()) ||
//						StringUtils.isEmpty(deal.getDealDate()) ||
//						StringUtils.isEmpty(deal.getAmount())
//						
//				){
//					inValidDeals.add(deal);
//				}
//				else{
//					validDeals.add(deal);	
//				}
//
//			}
	}
	


	@Test
	public void newFileUpload() throws FileNotFoundException, IOException{
		String METHOD_NAME = "newStorage";
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
        MockMultipartFile multipartFile1 = new MockMultipartFile("targetFile", 
				org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(file)));
        Assert.assertNotNull(multipartFile1);
		
		
	        try {
	        	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload")
		                .file(multipartFile1))
	        			.andDo(MockMvcResultHandlers.print())
	        			.andExpect(MockMvcResultMatchers.view().name("fileupload"))
						.andExpect(MockMvcResultMatchers.model().attributeExists("message"))
						.andExpect(MockMvcResultMatchers.status().isOk())
		            		.andReturn();
			} catch (Exception e) {
				e.printStackTrace();
			}		
	                    		
		Assert.assertNotNull(model.getModel());
	}
	
	
	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() {
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	    try {
			mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.view().name("fileupload"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	
}
