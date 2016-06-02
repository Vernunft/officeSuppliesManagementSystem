package edu.fjnu.mcs.cs2.orms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.SupplierType;
import edu.fjnu.mcs.cs2.orms.type.SupplierStatus;

/**
 * @Title: SupplierControllerTest.java
 * @Package edu.fjnu.mcs.cs2.orms.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lbb
 * @date 2016年6月1日 下午5:39:28
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml",
		"classpath*:applicationContext-shiro.xml" })
@ActiveProfiles("production")
public class SupplierControllerTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	// @Test
	public void addSupplierType() throws Exception {
		DTO data = new DTO();
		SupplierType supplierType = new SupplierType();
		supplierType.setName("体育用品");
		data.setSupplierType(supplierType);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/addSupplierType")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	// @Test
	public void deleteSupplierType() throws Exception {
		DTO data = new DTO();
		SupplierType supplierType = new SupplierType();
		supplierType.setId(73);
		data.setSupplierType(supplierType);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/deleteSupplierType")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	// @Test
	public void addSupplier() throws Exception {
		DTO data = new DTO();
		Supplier supplier = new Supplier();
		supplier.setName("新大陆");
		supplier.setContactName("陈三");
		supplier.setContactNumber("1562232523");
		supplier.setPostcode("362132");
		SupplierType supplierType = new SupplierType();
		supplierType.setId(10);
		supplier.setType(supplierType);
		data.setSupplier(supplier);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/addSupplier").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	// @Test
	public void getSupplier() throws Exception {
		DTO data = new DTO();
		Supplier supplier = new Supplier();
		// supplier.setName("福建东百集团股份有限公司");
		// supplier.setContactName("陈三");
		// supplier.setContactNumber("1562232523");
		// supplier.setPostcode("362132");
		SupplierType supplierType = new SupplierType();
		// supplierType.setId(10);
		supplier.setType(supplierType);
		List<Res> reses = new ArrayList<>();
		Res res = new Res();
		reses.add(res);
		supplier.setReses(reses);
		data.setCurrentPage(1);
		data.setSize(10);
		data.setSupplier(supplier);

		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();

		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/getSupplier").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	//@Test
	public void addSupplierSupplies() throws Exception {
		DTO data = new DTO();
		Supplier supplier = new Supplier();
		SupplierSupplies supplierSupplies = new SupplierSupplies();
		supplier.setId(2);
		supplierSupplies.setSupplier(supplier);
		Res res = new Res();
		res.setId(1);
		supplierSupplies.setRes(res);
		supplierSupplies.setPrice(1f);
		data.setSupplierSupplies(supplierSupplies);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/addSupplierSupplies").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	// getSupplierSupplies  modifySupplierSupplies
	public void deleteSupplierSupplies() throws Exception {
		DTO data = new DTO();
		Supplier supplier = new Supplier();
		SupplierSupplies supplierSupplies = new SupplierSupplies();
		//supplier.setId(2);
		//supplierSupplies.setSupplier(supplier);
		Res res = new Res();
		//res.setId(1);
		//supplierSupplies.setRes(res);
	//	supplierSupplies.setPrice(1f);
		supplierSupplies.setId(32);
		data.setSupplierSupplies(supplierSupplies);
		data.setCurrentPage(5);
		data.setSize(10);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);

		String json = mapper.writeValueAsString(data);

		System.out.println("Java2Json: " + json);

		data = mapper.readValue(json, DTO.class);

		System.out.println("Json2Java: " + mapper.writeValueAsString(data));

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/deleteSupplierSupplies").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}