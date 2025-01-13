package com.mk.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.mk.utils.ExcelUtils;

public class MethodInterceptor implements IMethodInterceptor{

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		
		List<IMethodInstance> result = new ArrayList<IMethodInstance>();
		
		
		List<Map<String, String>> a = ExcelUtils.getDataAsList("Sheet1");
		
//		for(int i =0;i<methods.size();i++) {
//			for(int j=0;j<a.size();j++) {
//				System.out.println(a.get(0).get("testName"));
//				if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(a.get(j).get("testName").trim())) {
//					if(a.get(j).get("execute").trim().equalsIgnoreCase("yes")) {
//						System.out.println("apple");
//						methods.get(i).getMethod().setInvocationCount(Integer.parseInt(a.get(j).get("count").trim()));
//						methods.get(i).getMethod().setPriority(Integer.parseInt(a.get(j).get("priority").trim()));
//						result.add(methods.get(i));
//					}
//				}
//			}
//			
//		}
		
		
	    for (IMethodInstance method : methods) {
            for (Map<String, String> data : a) {
                String excelTestName = data.get("testName").trim();
                String methodName = method.getMethod().getMethodName().trim();

                System.out.println("Method Name: " + methodName);
                System.out.println("Excel Test Name: " + excelTestName);

                if (methodName.equalsIgnoreCase(excelTestName)) {
                    if ("yes".equalsIgnoreCase(data.get("execute"))) {
                        System.out.println("Executing test: " + methodName);
                        System.out.println("Adding test to result: " + methodName);
                        // Set invocation count and priority
                        method.getMethod().setInvocationCount((int)Double.parseDouble(data.get("count")));
                        method.getMethod().setPriority((int)Double.parseDouble(data.get("priority")));

                        result.add(method);
                    }
                }
            }
        }
	    
		return result;
	}
	



}
