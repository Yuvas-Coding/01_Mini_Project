package com.vtalent.sevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtalent.Repository.PlanCateogryRepository;
import com.vtalent.Repository.PlanRepository;
import com.vtalent.entity.Plan;
import com.vtalent.entity.PlanCategory;

@Service
public class PlanServiceImpl  implements PlanService{
	
	@Autowired
	private PlanCateogryRepository planCateogryRepository;
	
	@Autowired
	private PlanRepository planRepository;

	@Override
	public Map<Integer, String> getPlanCategories() {
		// TODO Auto-generated method stub
		List<PlanCategory> categories = planCateogryRepository.findAll();
		
		Map<Integer, String> categoryMap = new HashMap<>();
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}
	
	

	@Override
	public boolean savePlan(Plan plan) {
		// TODO Auto-generated method stub
		Plan saved = planRepository.save(plan);

		return saved.getPlanId() != null;
	}
	
	

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepository.findAll();
	}
	
	

	@Override
	public Plan getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		Optional<Plan> findById = planRepository.findById(planId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	

	@Override
	public boolean updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		planRepository.save(plan);
		return plan.getPlanId()!= null;
	}
	
	

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			planRepository.deleteById(planId);
			status=true;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		// TODO Auto-generated method stub
		
		Optional<Plan> findById = planRepository.findById(planId);
		
		if(findById.isPresent()) {
			Plan plan = findById.get();
			
			plan.setActiveSwitch(status);
			planRepository.save(plan);
			return true;
		}
		
		return false;
	}

}
