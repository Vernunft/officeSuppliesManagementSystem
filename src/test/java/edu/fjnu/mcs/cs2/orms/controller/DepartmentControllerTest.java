package edu.fjnu.mcs.cs2.orms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

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
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Department;

/**   
 * @Title: DepartmentControllerTest.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年6月1日 下午9:01:52 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml",
		"classpath*:applicationContext-shiro.xml" })
@ActiveProfiles("production")
public class DepartmentControllerTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	//@Test
	public void addDepartment() throws Exception {
		DTO data = new DTO();
		Department department = new Department();
		department.setName("人力资源");
		Type department2 = new Type();
		department.setId(75);
		department.setParent(department2);
		data.setDepartment(department);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/addDepartment")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void deleteDepartment() throws Exception {
		DTO data = new DTO();
		Department department = new Department();
		department.setId(74);
		data.setDepartment(department);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/deleteDepartment")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void addEmpForDept() throws Exception {
		DTO data = new DTO();
		Employee employee = new Employee();
		employee.setId(1);
		Department department = new Department();
		department.setId(11);
		data.setDepartment(department);
		data.setEmployee(employee);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/addEmpForDept").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void getDepartmentInfo() throws Exception {
		DTO data = new DTO();
		Department department = new Department();
		department.setId(11);
		//department.setName("办公室");
		data.setDepartment(department);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);
		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/getDepartmentInfo").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
