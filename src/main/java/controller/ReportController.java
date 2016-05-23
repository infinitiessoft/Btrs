// package controller;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
//
// import sendto.UserSharedSendto;
// import service.ReportService;
// import service.UserService;
// import service.UserSharedService;
//
// @Controller
// @RequestMapping("/report/{owner_id}")
// public class ReportController {
// @Autowired
// ReportService reportService;
// UserService userService;
// UserSharedService userSharedService;
//
// @RequestMapping(value = { "/user/{username}/" }, method = RequestMethod.GET)
// public String myReport(@PathVariable String username, Model model) {
// UserSharedSendto userShared = userSharedService.findByUsername(username);
// // UserSendto user = userService.findByUserSharedId(userShared.getId());
// // ReportSendto report = reportService.findByOwnerId(user.getId());
// model.addAttribute("userData", userShared);
// // model.addAttribute("userReport", report);
// return "report";
// }
//
// }
