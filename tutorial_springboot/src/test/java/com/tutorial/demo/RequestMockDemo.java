package com.tutorial.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.SpringbootStart;
import com.tutorial.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author: jimmy
 * @Date: 2019/4/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
@WebAppConfiguration
public class RequestMockDemo extends MockMvcResultMatchers {
    //模拟mvc对象类.
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void saveUserTest() throws Exception {
        String url ="/mockSave";
        User user = new User();
        user.setId(1);
        user.setName("jimmy");
        user.setSex("men");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("mapper:"+mapper.writeValueAsString(user));
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult mvcResult = mockMvc.perform(request)
                //使用jsonPath解析返回值,判断具体的内容
                //  .andExpect(jsonPath("$.errorCode", is("301")))
                //.andDo(print()) //打印出请求和响应的内容
                .andReturn();
        int statusCode = mvcResult.getResponse().getStatus();
        System.out.println(statusCode+"-=========");
        Assert.assertEquals(statusCode, 200);
        System.out.println(mvcResult.getResponse().getErrorMessage()+"--");
        System.out.println(mvcResult.getResponse().getContentAsString()+"++++");//将相应的数据转换为字符串
    }
}
