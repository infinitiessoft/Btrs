package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String welcomePage(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		if (!requestURI.endsWith("/")) {
			return "redirect:/";
		}
		return "hello"; // jsp
	}

}
