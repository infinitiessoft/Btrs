package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sendto.UserSharedSendto;
import service.UserSharedService;

@Controller
public class AccountController {

	@Autowired
	UserSharedService userService;

	@RequestMapping(value = { "/account/{username}" }, method = RequestMethod.GET)
	public String createReport(@PathVariable String username, Model model) {
		UserSharedSendto user = userService.findByUsername(username);
		model.addAttribute("userData", user);
		return "account";
	}

}
