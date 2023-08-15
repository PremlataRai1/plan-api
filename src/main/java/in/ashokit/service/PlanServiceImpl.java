package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.entity.plan.PlanService;
import in.ashokit.exception.NoPlanFoundException;
import in.ashokit.repo.PlanCategoryRepo;
import in.ashokit.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanCategoryRepo planCategory;

	@Autowired
	PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> categories = planCategory.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {

		Plan saved = planRepo.save(plan);

		return saved.getPlanId() != null;

	}

	@Override
	public List<Plan> getAllPlan() {

		List<Plan> plans = planRepo.findAll();
		return plans;

	}

	@Override
	public Plan getPlanById(Integer planId) throws NoPlanFoundException {

		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			return findById.get();
		} else if(!findById.isPresent()) {
			return null;
		}
		else
			throw new NoPlanFoundException("InvALID plan id");
	}

	@Override
	public boolean updatePlan(Plan plan) {

		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status = false;
		try {
		      planRepo.deleteById(planId);
		      status = true;
		}
		catch(Exception e){
			e.printStackTrace();
			status = false;
			
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById != null) {
		Plan plan = findById.get();
		plan.setActiveSw(status);
		planRepo.save(plan);
		return true;
		}
		
		else 
			return false;
	}

}
