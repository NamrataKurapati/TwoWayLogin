package net.codejava;

import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.util.Random;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MainController {
	
 
 
		@GetMapping("/login")
		public String showForm(Model model)
		{
			User user = new User();
		    model.addAttribute("user", user);
		    
			return "login_form";
		}
	
		String name, email, onetp;
		
		
		@PostMapping("/login")
		public String submitForm(@ModelAttribute("user") User user) {
			System.out.println(user);
			EmailSenderService service = new EmailSenderService();
			
			String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
			System.out.println(otp);		
			
			name = user.getName();
			email = user.getEmail();
			onetp = otp;
			
		
			
			service.sendSimpleEmail(email, otp, "Hello OTP");
			
			
			return "otp";
		}
		 
	
		
		@PostMapping("/otp")
		public String submitOtp(@ModelAttribute("user") User user) {
			System.out.println(user); 
			
			String getot = user.getOtp();
			
			if(getot.equals(onetp))
			{
				user.setEmail(email);
				user.setName(name);
				return "login_success";
			}
			else
			{
				return "error_otp";
			}
				
			
		}
		
		
}
