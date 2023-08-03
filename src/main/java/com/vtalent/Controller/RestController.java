package com.vtalent.Controller;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vtalent.Constants.AppConstants;
import com.vtalent.Properties.AppProperties;
import com.vtalent.entity.Plan;
import com.vtalent.sevice.PlanService;



@org.springframework.web.bind.annotation.RestController
public class RestController {
	

	private PlanService planService;
	
	private Map<String ,  String> messages;
	
	public RestController(PlanService planService, AppProperties appProperties) {
		this.planService=planService;
		this.messages=appProperties.getMessages();
		System.out.println(this.messages);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		Map<Integer,String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody	 Plan plan){
		String respString=AppConstants.Empty_STR;
		
		boolean savePlan = planService.savePlan(plan);
		
		if(savePlan) {
			respString=messages.get(AppConstants.PLAN_SAVE_SUCC);
		}
		else {
			respString=messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(respString,HttpStatus.CREATED);
	}
	
	@GetMapping("/getPlans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	@GetMapping("/editPlan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan planbyId = planService.getPlanById(planId);
		return new ResponseEntity<Plan>(planbyId, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		
		boolean deletePlanById = planService.deletePlanById(planId);
		String msg=AppConstants.Empty_STR;
		
		if(deletePlanById) {
			msg=messages.get(AppConstants.PLAN_DELETE_SUCC);
			
		}
		else {
			msg=messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/plan/{planId}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		
		 boolean updatePlan = planService.updatePlan(plan);
		String msg=AppConstants.Empty_STR;
		
		if(updatePlan) {
			msg=messages.get(AppConstants.PLAN_UPDATE_SUCC);
			
		}
		else {
			msg=messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, String status) {

		boolean isplanStatusChange = planService.planStatusChange(planId, status);

		String msg = "";

		if (isplanStatusChange) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	
	
	

}
