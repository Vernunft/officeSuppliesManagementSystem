package edu.fjnu.mcs.cs2.orms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultDesktopManager;

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
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;
import edu.fjnu.mcs.cs2.orms.type.SpecificResStatus;

/**   
 * @Title: OutstockController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月31日 下午5:16:53 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml" ,	"classpath*:applicationContext-shiro.xml"})
@ActiveProfiles("production")
public class OutstockControllerTest {
	@Autowired
	WebApplicationContext wac;
	

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	//@Test
	public void addOutstockType() throws Exception {
		DTO data = new DTO();
		OutstockType outstockType = new OutstockType();
		outstockType.setName("闲置");
		data.setOutstockType(outstockType);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/addOutstockType")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	//@Test
	public void deleteOutstockType() throws Exception {
		DTO data = new DTO();
		OutstockType outstockType = new OutstockType();
		outstockType.setId(78);
		data.setOutstockType(outstockType);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/deleteOutstockType")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	//@Test
	public void addOutstock() throws Exception {
		DTO data = new DTO();
		Outstock outstock = new Outstock();
		Department department = new Department();
		outstock.setDepartment(department);
		Employee employee = new Employee();
		employee.setId(1);
		outstock.setEmployee(employee);
		outstock.setMake(employee);
		List<SpecificRes> specificRess = new ArrayList<>() ;
		Res res= new Res();
		res.setId(1000);
		SpecificResStatus status = new SpecificResStatus();
		status.setId(18);
		SpecificRes specificRes = new SpecificRes(res, status, 25.6f);
		specificRes.setId(1);
		specificRess.add(specificRes);
		outstock.setSpecificReses(specificRess);
		outstock.setDate(new Date());
		OutstockType outstockType = new OutstockType();
		outstockType.setId(15);
		outstock.setType(outstockType);
		data.setOutstock(outstock);
		//将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/addOutstock").contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getOutstockInfo() throws Exception {
		DTO data = new DTO();
		Outstock outstock = new Outstock();
		Employee employee = new Employee();
		//employee.setId(1);
		Employee make = new Employee();
	//	employee.setName("向珊");
		outstock.setEmployee(employee);
		//make.setId(1);
		//make.setName("张枫震");
		outstock.setMake(make);
		//instock.setMake(make);
		List<SpecificRes> specificRess = new ArrayList<>() ;
		Res res= new Res();
//		res.setId(1000);
		SpecificResStatus status = new SpecificResStatus();
//		status.setId(18);
		SpecificRes specificRes = new SpecificRes();
				//new SpecificRes(res, status, 25.6f);
		//specificRes.setId(1);
		specificRess.add(specificRes);
		outstock.setSpecificReses(specificRess);
//		instock.setDate(new Date());
		Map<String, Object> map = new HashMap<>();
//		map.put("beginTime", "2014-10-30");
//		map.put("endTime", "2015-06-05");
		data.setMap(map);
		OutstockType outstockType = new OutstockType();
	//	instockType.setId(15);
		//instockType.setName("购入");
		outstock.setType(outstockType);
		Department department = new Department();
		outstock.setDepartment(department);
		data.setOutstock(outstock);
		data.setCurrentPage(2);
		data.setSize(10);
		//将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/getOutstockInfo").contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}

