package in.ashokit.entity.plan;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.exception.NoPlanFoundException;

@Service
public interface PlanService {
	
	public Map<Integer, String> getPlanCategories();
	public boolean savePlan(Plan plan);
	public List<Plan> getAllPlan();
	public Plan getPlanById(Integer planId) throws NoPlanFoundException;
	public boolean updatePlan(Plan plan);
	public boolean deletePlanById(Integer planId);
	public boolean planStatusChange(Integer planId, String status);

}
