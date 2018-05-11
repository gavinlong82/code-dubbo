package com.ytd.mds.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ytd.mds.v1.IUserService;
import com.ytd.mds.v1.pojo.UserModel;

@Controller
@EnableAutoConfiguration
public class SampleController {
	
	@Autowired
	private IUserService userService;
	
	
	// 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
	@Value("${application.hell:Hello Shanhy}")
	private String hello = "Hello Shanhy";

	/**
	 * * 默认页<br/>
	 * @RequestMapping("/") 和 @RequestMapping 是有区别的
	 * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。 如果加了参数“/”，则只认为是根页面。
	 * 
	 * @return
	 * @author gavinlong
	 */
	@RequestMapping("/")
	String index(Map<String, Object> model) {
		model.put("time", new Date());
        System.out.println(userService);
        UserModel user =  userService.getUserById("test");
        System.out.println(user);
        model.put("msg",user);
		return "/home";
	}

	/**
	 * 
	 * 直接返回字符
	 * @return
	 * @author gavinlong
	 */
	@RequestMapping("/testModel")
	String index(ModelMap map) {
		map.addAttribute("msg", "Spring boot");
		return "/home";
	}
	
	/**
	 * 
	 * 直接返回字符
	 * @return
	 * @author gavinlong
	 */
	@RequestMapping("/test")
	@ResponseBody
	String test() {
		return "测试直接返回字符";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleController.class, args);
	}
}
