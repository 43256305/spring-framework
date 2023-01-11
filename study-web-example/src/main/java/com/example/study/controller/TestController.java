package com.example.study.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
@SessionAttributes("name")
public class TestController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

//	最终的访问路径为：tomcat配置的application context + web.xml中的url-pattern + 此controller的requestMapping
	@GetMapping
	public String test(){
		//可以看到，当两个DispatcherServlet时，这里容器的hashCode是不同的
		System.out.println(applicationContext.hashCode());
		return "Hello World!";
	}

	@PostMapping
	public String adapterSupportMethodTest(){
		return "Hello World!";
	}

	@GetMapping("/initBinderTest")
	public String initBinderTest(Date date){
		return date.toString();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		//通过此方法，我们能够将传入的yyyy-MM-dd格式的字符串转换成Date对象  注意，此binder只在此controller有效
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}


	@ModelAttribute("user")
	public String addString(@RequestParam("value") String value){
		//此方法并不能通过URL访问，作用为给此controller的所有HandlerMethod的Model增加key-value的作用，同样也能定义到@ControllerAdvice中作为全局使用
		//在这里，我们能为传入的值做一些前置处理
		return value + "qqq";
	}

	@GetMapping("/modelAttr")
	public String getModelAttr(Model model){
		System.out.println(model.getAttribute("name"));
		//访问此HandlerMethod时，将必须要传入value参数（即上面的addString方法定义的），传入value参数后，我们就能够通过此方法的model中获取(user,value)的键值对了
		return (String) model.getAttribute("user");
	}

	@GetMapping("/setSessionAttr")
	public void setSessionAttr(HttpServletRequest request){
		//使用@SessionAttributes("name")能够将name这个key放入到model中，放入到session中name属性后，以后的所有HandlerMethod都能通过model获取到name属性
		request.getSession().setAttribute("name", "name");
	}


	 @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//这里注入的即是XmlWebApplicationContext
		this.applicationContext = applicationContext;
	}
}
