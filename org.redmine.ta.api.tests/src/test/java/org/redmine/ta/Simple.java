package org.redmine.ta;

import java.util.Date;
import java.util.List;

import org.redmine.ta.beans.Issue;
import org.redmine.ta.beans.TimeEntry;
import org.redmine.ta.beans.User;
import org.redmine.ta.internal.RedmineXMLGenerator;
import org.redmine.ta.internal.RedmineXMLParser;

public class Simple {
//	private static String redmineHost = "https://www.hostedredmine.com";
//	private static String apiAccessKey = "a3221bfcef5750219bd0a2df69519416dba17fc9";
    
	private static String redmineHost = "http://192.168.0.30:3000";
    private static String apiAccessKey = "cf6de1494fcca2b5c9206179f6621abeea15c6c7";

	private static String projectKey = "taskconnector-test";
	private static Integer queryId = null; // any

	public static void main(String[] args) {
		RedmineManager mgr = new RedmineManager(redmineHost, apiAccessKey);
		try {
//			tryGetIssues(mgr);
//			printCurrentUser(mgr);
//			generateXMLForUser();
			generateXMLForTimeEntry();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateXMLForTimeEntry() {
		TimeEntry o = new TimeEntry();
		o.setId(13);
		o.setIssueId(45);
		o.setActivityId(3);
		o.setProjectId(55);
		o.setUserId(66);
		o.setHours(123f);
		o.setComment("text here");
		o.setSpentOn(new Date());
		String xml = RedmineXMLGenerator.toXML(o);
		System.out.println(xml);
	}

	private static void generateXMLForUser() {
		User u = new User();
		u.setLogin("newlogin");
		String xml = RedmineXMLGenerator.toXML(u);
		System.out.println(xml);
		
	}

	private static void tryGetIssues(RedmineManager mgr) throws Exception {
		List<Issue> issues = mgr.getIssues(projectKey, queryId);
		for (Issue issue : issues) {
			System.out.println(issue.toString());
		}
	}

	private static void printCurrentUser(RedmineManager mgr) throws Exception {
		User currentUser = mgr.getCurrentUser();
		System.out.println("user=" + currentUser.getMail());
		
		currentUser.setMail("ne@com123.com");
		mgr.updateUser(currentUser);
		System.out.println("updated user");

		User currentUser2 = mgr.getCurrentUser();
		System.out.println("updated user's mail: " + currentUser2.getMail());

	}
}