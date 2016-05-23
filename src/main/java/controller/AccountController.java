// package controller;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
//
// import sendto.UserSharedSendto;
// import service.UserSharedService;
//
// @Controller
// public class AccountController {
//
// private final Logger logger =
// LoggerFactory.getLogger(AccountController.class);
//
// @Autowired
// private UserSharedService userService;
//
// @RequestMapping(value = { "/account/{username}" }, method =
// RequestMethod.GET)
// public String createReport(@PathVariable String username, Model model) {
// UserSharedSendto user = userService.findByUsername(username);
// model.addAttribute("userData", user);
// return "account";
// }
//
// @RequestMapping(value = { "/account/{id}/update" }, method =
// RequestMethod.POST)
// public String updateReport(@PathVariable("id") long id, @ModelAttribute
// UserSharedSendto userShared, Model model) {
// logger.debug("updateReport() : {}", id, userShared);
// userService.update(id, userShared);
// return "hello";
// }
// }
