package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.entity.Coach;
import com.entity.Course;
import com.manager.CoachManager;
import com.opensymphony.xwork2.ActionSupport;

public class ShowCoachAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 308162775804674346L;
	
	private CoachManager coachManager;
	private List<Coach> coaches;
	private List<String> sportsList;

	public List<String> getSportsList() {
		return sportsList;
	}

	public void setSportsList(List<String> sportsList) {
		this.sportsList = sportsList;
	}
	
	public CoachManager getCoachManager() {
		return coachManager;
	}

	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}
	
	public List<Coach> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}

	public String execute(){
		coaches = coachManager.getAllCoach();
		sportsList = new ArrayList<String>();
		if(!coaches.isEmpty()){
			for(Coach tmp:coaches){
				if(!sportsList.contains(tmp.getSportsName())){
					sportsList.add(tmp.getSportsName());
				}
			}
		}
		return SUCCESS;
	}
	
	public void appexecute() throws IOException{
		coaches = coachManager.getAllCoach();
		JSONArray jsonArray = JSONArray.fromObject(coaches);  
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		out.println(jsonArray.toString());  
		out.flush();  
		out.close(); 
	}
}
