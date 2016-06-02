package edu.fjnu.mcs.cs2.orms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.InstockType;

/**   
 * @Title: CategotyControllerTest.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月31日 下午2:53:58 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml" ,	"classpath*:applicationContext-shiro.xml"})
@ActiveProfiles("production")
public class CategotyControllerTest {
	@Autowired
	WebApplicationContext wac;
	

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

//	@Test
	public void getCategoryInfo() throws Exception {
		DTO data = new DTO();
		Category category  = new Category();
		category.setId(58);
		data.setCategory(category);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/getCategoryInfo")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	//s@Test
	public void getAllCategory() throws Exception {
		DTO data = new DTO();
//		Category category  = new Category();
//		category.setId(58);
//		data.setCategory(category);
		data.setCurrentPage(2);
		data.setSize(10);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/getAllCategory")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	//@Test
	public void addCategory() throws Exception {
		DTO data = new DTO();
		Category category  = new Category();
//		category.setId(58);
		category.setName("空调");
		data.setCategory(category);
//		data.setCurrentPage(2);
//		data.setSize(10);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/addCategory")  
						.contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	public void deleteCategory() throws Exception {
		DTO data = new DTO();
		Category category  = new Category();
		category.setId(75);
		//category.setName("空调");
		data.setCategory(category);
//		data.setCurrentPage(2);
//		data.setSize(10);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/deleteCategory")  
						.contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}