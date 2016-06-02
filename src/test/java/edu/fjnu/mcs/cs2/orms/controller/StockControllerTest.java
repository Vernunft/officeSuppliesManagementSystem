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
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.Education;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;
import edu.fjnu.mcs.cs2.orms.type.Unit;

/**   
 * @Title: StockControllerTest.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年6月2日 下午11:28:39 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml",
		"classpath*:applicationContext-shiro.xml" })
@ActiveProfiles("production")
public class StockControllerTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void getExceedMaxLimitRes() throws Exception {
		DTO data = new DTO();
		EmpWorkStatus empWorkStatus = new EmpWorkStatus();
		empWorkStatus.setName("请假");
		data.setEmpWorkStatus(empWorkStatus);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/getExceedMaxLimitRes")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void addRes() throws Exception {
		DTO data = new DTO();
		Res res =new Res();
		Category category = new Category();
		category.setId(10);
		res.setCategory(category);
		Unit unit = new Unit();
		unit.setName("把");
		res.setUnit(unit);
		res.setName("空调");
		res.setModel("格力");
		data.setRes(res);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/addRes").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void modifyRes() throws Exception {
		DTO data = new DTO();
		Res res =new Res();
		res.setId(2001);
		Category category = new Category();
		category.setId(22);
		res.setCategory(category);
		data.setRes(res);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);
		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/modifyRes").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
