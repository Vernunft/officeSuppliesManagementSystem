package edu.fjnu.mcs.cs2.orms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/springmvc.xml","classpath*:applicationContext.xml"})
@ActiveProfiles("production")
public class InstockControllerTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before()
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void loginTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
				.param("account", "1012013001")
				.param("password", "123456"))
				.andExpect(status().isOk())
				// .andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
