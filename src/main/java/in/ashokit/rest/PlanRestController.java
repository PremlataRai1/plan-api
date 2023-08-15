package in.ashokit.rest;

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
import org.springframework.web.bind.annotation.RestController;



import in.ashokit.entity.Plan;
import in.ashokit.entity.plan.PlanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanRestController {

	@Autowired
	PlanService planService;

	@GetMapping("/Categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<>(planCategories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		boolean savePlan = planService.savePlan(plan);
		String status = "";
		if (savePlan == true) {
			status = "Plan saved!";
		} else {
			status = "Plan not saved!";
		}
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> Plans() {
		List<Plan> allPlan = planService.getAllPlan();

		return new ResponseEntity<>(allPlan, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planService.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> update(@RequestBody Plan plan) {

		boolean isUpdated = planService.updatePlan(plan);

		String msg = "";
		if (isUpdated == true) {
			msg = "Plan updated";
		} else {
			msg = "Plan not updated";
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = "";
		if (isDeleted == true) {
			msg = "Plan deleted";
		} else {
			msg = "Plan not deleted";
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);
       }

	@PutMapping("change_status")
	public ResponseEntity<String> statuschange(@PathVariable Integer planId, String status) {
		
		boolean planStatusChange = planService.planStatusChange(planId, status);
		
		String msg = "";
		if(planStatusChange == true) {
			msg = "Plan status changed";
		}
		else {
			msg = "Plan status not changed";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
