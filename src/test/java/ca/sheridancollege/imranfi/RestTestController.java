package ca.sheridancollege.imranfi;



import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.imranfi.Beans.Student;

@SpringBootTest
@AutoConfigureMockMvc
public class RestTestController {
	@Autowired
	private MockMvc mockMvc;
	
	//GET url: /students : -> list of non empty students. 
	
	@Test
	public void testGetCollection() throws Exception {
		//define url
		String url = "/students";
		
		//test url with get request 
	
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		//test is done successfully
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200, status);
		
		//reterieve json val and convert it into appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		Student[] students = new ObjectMapper().readValue(content,Student[].class );
		
		//check for records. 
		assertTrue(students.length > 0);
		
	}
	
	//test for student that exists 
	@Test
	public void testGetStudentBYId() throws Exception {
		String url = "/students/1";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		//test is done successfully
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200, status);
		
		//reterieve json val and convert it into appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		
		//check for records. 
		assertTrue(content != null);
		
	}
	
	//test for student that doesnt exists 
	@Test
	public void testGetElementDoesntExist() throws Exception {
		String url = "/students/100";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		//test is done successfully
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200, status);
		
		//reterieve json val and convert it into appropriate type
		String content = mvcResult.getResponse().getContentAsString();
		
		//nothing is returned.
		//note json returns empty string as null= ""
		
		assertTrue(content.equals(""));
	}
	
	
	//test post: sends json result: string
		@Test
		public void testPostElement() throws Exception {
			String url = "/students";
			
			Student s = new Student("meow",77);
			//note we have to send json 
			
			String studentjson = new ObjectMapper().writeValueAsString(s);
			
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(studentjson))
					.andReturn();
			
			//test is done successfully
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			
			//reterieve json val and convert it into appropriate type
			String content = mvcResult.getResponse().getContentAsString();
			
			//nothing is returned.
			//note json returns empty string as null= ""
			
			assertTrue(content.equals("Student was added"));
		}
	
		//put
		@Test
		public void testEditStudentBYId() throws Exception {
			String url = "/students/2";
			
			Student s = new Student(2, "meow",98, "A");
			String studentjson = new ObjectMapper().writeValueAsString(s);
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(studentjson))
					.andReturn();
			
			
			//test is done successfully
			int status = mvcResult.getResponse().getStatus();
			
			assertEquals(200, status);
			
			//reterieve json val and convert it into appropriate type
			String content = mvcResult.getResponse().getContentAsString();
			
			//check for records. 
			
			assertTrue(content.equals("Student is updated"));
		}
		

		

		
		//delete
		@Test
		public void testDeleteDStudentById() throws Exception {
			String url = "/students/1";
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)
					.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			//test is done successfully
			int status = mvcResult.getResponse().getStatus();
			
			assertEquals(200, status);
			
			//reterieve json val and convert it into appropriate type
			String content = mvcResult.getResponse().getContentAsString();
	
			//check for records. 
			assertTrue(content.equals("requested student is deleted"));
			
		}
	
		//delete
				@Test
				public void testDeleteAllStudents() throws Exception {
					String url = "/students";
					
					MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)
							.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
					
					//test is done successfully
					int status = mvcResult.getResponse().getStatus();
					
					assertEquals(200, status);
					
					//reterieve json val and convert it into appropriate type
					String content = mvcResult.getResponse().getContentAsString();
			
					//check for records. 
					assertTrue(content.equals("Students are deleted"));
					
				}
	
	
}
