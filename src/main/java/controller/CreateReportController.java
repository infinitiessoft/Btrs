package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sendto.ReportSendto;
import service.ReportService;

@Controller
public class CreateReportController {

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public String createReport() {
		return "step";
	}

	@RequestMapping(value = { "/report/add" }, method = RequestMethod.POST)
	public String addReportFromForm(@Valid ReportSendto report, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new_report";
		}
		reportService.save(report);
		return "hello";
	}
}
