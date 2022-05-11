package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Billing empObj = new Billing();

	public BillingAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Billing empobj = new Billing();
		
		String output = "";
		output = empobj.readBillings();
		
		response.getWriter().append(output);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String line, requestbody = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while ((line = br.readLine()) != null){
        	
        	requestbody+=line;
        }

        JsonObject Object = new JsonParser().parse(requestbody).getAsJsonObject();
		String output = empObj.insertBillings(
				Object.get("billingtype").getAsString(),
				Object.get("account_number").getAsString(),
				Object.get("totalprice").getAsString(),
				Object.get("remark").getAsString(),
				Object.get("date").getAsString());
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String line, requestbody = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while ((line = br.readLine()) != null){
        	
        	requestbody+=line;
        }

        JsonObject Object = new JsonParser().parse(requestbody).getAsJsonObject();
		String output = "";
		output = empObj.updateBillings(
				Object.get("hididSave").getAsString(),
				Object.get("billingtype").getAsString(),
				Object.get("account_number").getAsString(),
				Object.get("totalprice").getAsString(),
				Object.get("remark").getAsString(), 
				Object.get("date").getAsString());
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = "";
		
		if (paras.get("id") != null) {
			output = empObj.deleteBillings(paras.get("id").toString());
		}
		else {
			
			output = empObj.deleteBillings(request.getParameter("id"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
