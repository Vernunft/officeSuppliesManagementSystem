package edu.fjnu.mcs.cs2.orms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jca.work.WorkManagerTaskExecutor;
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
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.Education;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;

/**   
 * @Title: EmployeeControllerTest.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年6月1日 下午10:04:09 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml",
		"classpath*:applicationContext-shiro.xml" })
@ActiveProfiles("production")
public class EmployeeControllerTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	//@Test
	public void addEmpWorkStatus() throws Exception {
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

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/addEmpWorkStatus")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void deleteEmpWorkStatus() throws Exception {
		DTO data = new DTO();
		EmpWorkStatus empWorkStatus = new EmpWorkStatus();
		empWorkStatus.setId(73);
		data.setEmpWorkStatus(empWorkStatus);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/deleteEmpWorkStatus")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void addEmployee() throws Exception {
		DTO data = new DTO();
		Employee employee = new Employee();
		EmpWorkStatus empWorkStatus = new EmpWorkStatus();
		empWorkStatus.setId(1);
		employee.setWorkStatus(empWorkStatus);
		//employee.setId(1);
		Department department = new Department();
		department.setId(11);
		employee.setDepartment(department);
		Education education = new Education();
		education.setId(6);
		employee.setEducation(education);
		employee.setMobile("15236323623");
		employee.setName("李冰冰");
		employee.setIdCard("142252236355854412");
		employee.setSex(false);
		employee.setBirthplace("福建泉州");
		employee.setBirthplace("泉州");
		employee.setGraduated("师大");
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
				MockMvcRequestBuilders.post("/back/addEmployee").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void modifyEmpWorkStatus() throws Exception {
		DTO data = new DTO();
		Employee employee = new Employee();
		employee.setId(363);
		EmpWorkStatus empWorkStatus = new EmpWorkStatus();
		empWorkStatus.setId(1);
		data.setEmpWorkStatus(empWorkStatus);
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
				MockMvcRequestBuilders.post("/back/modifyEmpWorkStatus").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
