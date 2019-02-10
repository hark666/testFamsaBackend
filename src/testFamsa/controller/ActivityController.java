package testFamsa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import testFamsa.dao.ActivityDAO;
import testFamsa.model.ActivityModel;
import testFamsa.pojo.Activity;

import org.springframework.http.MediaType;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class ActivityController {
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getActivity() {
		ArrayList<Activity> result = new ArrayList<Activity>();
		
		ActivityDAO aDAO = new ActivityDAO();
		result = (ArrayList<Activity>) aDAO.displayRecords();
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String postActivity(@RequestBody ActivityModel activityModel) {
		ActivityDAO aDAO = new ActivityDAO();
		Integer result = new Integer(0);
		
		Activity act = new Activity();
		act.setActivityDescription(activityModel.getDescription());
		act.setActivityId(aDAO.displayRecords().size()+1);
		
		result = aDAO.createRecord(act);
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String patchActivity(@RequestBody ActivityModel activityModel) {
		ActivityDAO aDAO = new ActivityDAO();
		Activity actObj = new Activity();
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		actObj.setActivityId(activityModel.getId());
		actObj.setActivityDescription(activityModel.getDescription());
		aDAO.updateRecord(actObj);
		
		return gson.toJson("Success");
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deleteActivity(@RequestParam Integer id) {
		ActivityDAO aDAO = new ActivityDAO();
		aDAO.deleteRecord(id);
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		return gson.toJson("success");
	}
}