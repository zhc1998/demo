package com.jk.log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Highcharts;
import com.jk.service.XxfService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Aspect
@Component
public class SysLogAspect {
	 @Reference
	 private XxfService xxfService;


	
	@Pointcut("execution(* com.jk.controller.ToShowController.tohbtree())")
	public void logPointCut() {}
	 //后置通知
    @AfterReturning( value= "logPointCut()" ,returning="returningValue" ) 
    public void myAfter(JoinPoint jp,Object returningValue) throws UnknownHostException {//returningValue是返回值，但需要告诉spring
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String time = sdf.format(date);
		Highcharts highcharts2=xxfService.queryHighcharts(time);
		if(highcharts2==null){
			Highcharts highcharts = new Highcharts();
			highcharts.setDaytime(new Date());
			highcharts.setVisitcount(1);
			xxfService.addHighcharts(highcharts);
		}else {
			xxfService.updateHighcharts(highcharts2.getId());
		}




        	


    }
	  
	 

}
