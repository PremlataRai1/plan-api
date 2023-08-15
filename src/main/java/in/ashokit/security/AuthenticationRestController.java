package in.ashokit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	MyUserDetailService myUserDetailService;
	
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public String authenticateUser(@RequestBody AuthenticationRequest request) throws Exception {
		try {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}
		catch(Exception e) {
			throw new Exception("Invalid Credential");
		}
		
		UserDetails userDetails = myUserDetailService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.genereteToken(userDetails);
		
		return token;
	}
}
