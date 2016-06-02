package edu.fjnu.mcs.cs2.orms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.SpecificResDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.SpecificResStatus;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hamcrest.core.Is;

/**
 * @Title: InstockControllerTest.java
 * @Package edu.fjnu.mcs.cs2.orms.controller
 * @Description: TODO(入库控制层测试)
 * @author lbb
 * @date 2016年5月25日 下午12:52:07
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml" ,	"classpath*:applicationContext-shiro.xml"})
@ActiveProfiles("production")
public class InstockControllerTest {
	@Autowired
	WebApplicationContext wac;
	

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	//@Test
	public void addInstockType() throws Exception {
		DTO data = new DTO();
		InstockType instockType = new InstockType();
		instockType.setName("已入库");
		instockType.setRemark("入库类型");
		data.setInstockType(instockType);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/addInstockType")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
//	@Test
	public void deleteInstockType() throws Exception {
		DTO data = new DTO();
		InstockType instockType = new InstockType();
		instockType.setId(77);
		data.setInstockType(instockType);
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/deleteInstockType")  .contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	//@Test
	public void addInstock() throws Exception {
		DTO data = new DTO();
		Instock instock = new Instock();
		Supplier supplier = new Supplier();
		supplier.setId(1);
		instock.setSupplier(supplier);
		Employee employee = new Employee();
		employee.setId(1);
		instock.setAttn(employee);
		List<SpecificRes> specificRess = new ArrayList<>() ;
		Res res= new Res();
		res.setId(1000);
		SpecificResStatus status = new SpecificResStatus();
		status.setId(18);
		SpecificRes specificRes = new SpecificRes(res, status, 25.6f);
		specificRess.add(specificRes);
		instock.setSpecificReses(specificRess);
		instock.setDate(new Date());
		InstockType instockType = new InstockType();
		instockType.setId(15);
		instock.setType(instockType);
		data.setInstock(instock);
		//将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/addInstock").contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getInstockInfo() throws Exception {
		DTO data = new DTO();
		Instock instock = new Instock();
		Supplier supplier = new Supplier();
	//	supplier.setId(1);
	//	supplier.setName("环球批发有限公司");
		instock.setSupplier(supplier);
		Employee employee = new Employee();
		//employee.setId(1);
		Employee make = new Employee();
	//	employee.setName("向珊");
		instock.setAttn(employee);
		//make.setId(1);
		//make.setName("张枫震");
		instock.setMake(make);
		//instock.setMake(make);
		List<SpecificRes> specificRess = new ArrayList<>() ;
		Res res= new Res();
//		res.setId(1000);
		SpecificResStatus status = new SpecificResStatus();
		
//		status.setId(18);
		SpecificRes specificRes = new SpecificRes(res, status, 25.6f);
		//specificRes.setId(1);
		specificRess.add(specificRes);
		instock.setSpecificReses(specificRess);
//		instock.setDate(new Date());
		Map<String, Object> map = new HashMap<>();
//		map.put("beginTime", "2014-10-30");
//		map.put("endTime", "2015-06-05");
		data.setMap(map);
		InstockType instockType = new InstockType();
	//	instockType.setId(15);
	//	instockType.setName("购入");
		instock.setType(instockType);
		data.setSize(1);
		data.setCurrentPage(3);
		data.setInstock(instock);
		//将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/back/getInstockInfo").contentType(MediaType.APPLICATION_JSON)
			            .content(json))
			            .andExpect(status().isOk())
			            .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
