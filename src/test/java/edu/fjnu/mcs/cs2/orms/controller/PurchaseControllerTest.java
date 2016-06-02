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
import edu.fjnu.mcs.cs2.orms.entity.Purchase;
import edu.fjnu.mcs.cs2.orms.entity.PurchaseRes;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;
import edu.fjnu.mcs.cs2.orms.type.Unit;

/**   
 * @Title: PurchaseControllerTest.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年6月3日 上午12:26:27 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/springmvc.xml", "classpath*:applicationContext.xml" ,	"classpath*:applicationContext-shiro.xml"})
@ActiveProfiles("production")
public class PurchaseControllerTest {

	MockMvc mockMvc;
	@Autowired
	WebApplicationContext wac;

	//@Test
	public void addPurchase() throws Exception {
		DTO data = new DTO();
		Purchase purchase = new Purchase();
		Employee pic = new Employee();
		pic.setId(1);
		purchase.setPic(pic);
		PurchaseRes purchaseRes = new PurchaseRes();
		Res res = new Res();
		res.setId(1);
		purchaseRes.setResCount(20);
		purchaseRes.setResPrice(5f);
		purchaseRes.setRes(res);
		List<PurchaseRes> purchaseReses = new ArrayList<>();
		purchaseReses.add(purchaseRes);
		purchase.setPurchaseRes(purchaseReses);
		data.setPurchase(purchase);
		// 将对象转换成json
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);
	
		String json = mapper.writeValueAsString(data);
	
		System.out.println("Java2Json: " + json);
	
		data = mapper.readValue(json, DTO.class);
	
		System.out.println("Json2Java: " + mapper.writeValueAsString(data));
	
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/back/addPurchase").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void getPurchase() throws Exception {
		DTO data = new DTO();
		Purchase purchase = new Purchase();
		//purchase.setId(1004);
		Employee pic = new Employee();
		//pic.setId(37);
		purchase.setPic(pic);
		PurchaseRes purchaseRes = new PurchaseRes();
		Res res = new Res();
		//res.setId(1);
		purchaseRes.setResCount(20);
		purchaseRes.setResPrice(5f);
		purchaseRes.setRes(res);
		List<PurchaseRes> purchaseReses = new ArrayList<>();
		purchaseReses.add(purchaseRes);
		//purchase.setComplete(true);
		purchase.setPurchaseRes(purchaseReses);
		data.setPurchase(purchase);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// Boolean.TRUE);
	
		String json = mapper.writeValueAsString(data);
	
		System.out.println("Java2Json: " + json);
	
		data = mapper.readValue(json, DTO.class);
	
		System.out.println("Json2Java: " + mapper.writeValueAsString(data));
	
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/back/getPurchase")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

}
