package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import resources.specification.AttendRecordSpecification;
import resources.specification.EmployeeSpecification;
import resources.specification.ReportSpecification;
import resources.specification.UserRoleSpecification;
import resources.specification.UserSpecification;
import sendto.ReportSendto;
import sendto.ReportSummarySendto;
import sendto.StatusChangeSendto;
import service.MailService;
import service.ReportService;
import util.MailWritter;
import attendance.dao.AttendRecordDao;
import attendance.dao.EmployeeDao;
import attendance.entity.AttendRecord;
import attendance.entity.Employee;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import dao.ExpenseDao;
import dao.ExpenseTypeDao;
import dao.ParameterValueDao;
import dao.ReportDao;
import dao.StatusChangeDao;
import dao.UserDao;
import dao.UserRoleDao;
import entity.Expense;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.ParameterValue;
import entity.Report;
import entity.RoleEnum;
import entity.StatusChange;
import entity.StatusEnum;
import entity.User;
import entity.UserRole;
import exceptions.AttendRecordNotFoundException;
import exceptions.BadRequestException;
import exceptions.ReportNotFoundException;
import exceptions.UserNotFoundException;

public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory
			.getLogger(ReportServiceImpl.class);
	private ReportDao reportDao;
	private AttendRecordDao recordDao;
	private ExpenseDao expenseDao;
	private ExpenseTypeDao expenseTypeDao;
	private ParameterValueDao parameterValueDao;
	private StatusChangeDao statusChangeDao;
	private UserDao userDao;
	private UserRoleDao userRoleDao;
	private EmployeeDao employeeDao;
	private MailService mailService;
	private MailWritter writter;

	public ReportServiceImpl(ReportDao reportDao, AttendRecordDao recordDao,
			ExpenseDao expenseDao, ExpenseTypeDao expenseTypeDao,
			ParameterValueDao parameterValueDao,
			StatusChangeDao statusChangeDao, UserDao userDao,
			UserRoleDao userRoleDao, EmployeeDao employeeDao,
			MailService mailService, MailWritter writter) {
		this.reportDao = reportDao;
		this.recordDao = recordDao;
		this.expenseDao = expenseDao;
		this.expenseTypeDao = expenseTypeDao;
		this.parameterValueDao = parameterValueDao;
		this.statusChangeDao = statusChangeDao;
		this.userDao = userDao;
		this.userRoleDao = userRoleDao;
		this.employeeDao = employeeDao;
		this.mailService = mailService;
		this.writter = writter;

	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto retrieve(long id) {
		Report report = reportDao.findOne(id);
		if (report == null) {
			throw new ReportNotFoundException(id);
		}
		return toReportSendto(report, true);
	}

	private ReportSendto toReportSendto(Report report, boolean detail) {
		ReportSendto ret = new ReportSendto();
		ret.setId(report.getId());
		ret.setAttendanceRecordId(report.getAttendanceRecordId());
		ret.setComment(report.getComment());
		ret.setCreatedDate(report.getCreatedDate());
		ret.setEndDate(report.getEndDate());
		ret.setLastUpdatedDate(report.getLastUpdatedDate());
		ret.setMaxIdLastMonth(report.getMaxIdLastMonth());
		ret.setReason(report.getReason());
		ret.setRoute(report.getRoute());
		ret.setStartDate(report.getStartDate());
		ret.setCurrentStatus(report.getCurrentStatus().name());
		ret.setFirmOrProject(report.getFirmOrProject());

		if (detail) {
			ReportSendto.User usr = new ReportSendto.User();
			usr.setId(report.getOwner().getId());
			Employee owner = employeeDao.findOne(report.getOwner()
					.getUserSharedId());
			String name = owner.getName();
			usr.setName(name);
			ret.setOwner(usr);

			if (report.getReviewer() != null) {
				ReportSendto.User reviewer = new ReportSendto.User();
				reviewer.setId(report.getReviewer().getId());
				ret.setReviewer(reviewer);
			}

			for (Expense expense : report.getExpenses()) {
				ReportSendto.Expense e = new ReportSendto.Expense();
				e.setId(expense.getId());
				e.setComment(expense.getComment());
				ReportSendto.Expense.ExpenseType type = new ReportSendto.Expense.ExpenseType();
				type.setId(expense.getExpenseType().getId());
				type.setValue(expense.getExpenseType().getValue());
				e.setExpenseType(type);
				e.setTotalAmount(expense.getTotalAmount());
				e.setTaxAmount(expense.getTaxAmount());
				for (ParameterValue parameterValue : expense
						.getParameterValue()) {
					ReportSendto.Expense.ParameterValue value = new ReportSendto.Expense.ParameterValue();
					ReportSendto.Expense.ParameterValue.TypeParameter typePara = new ReportSendto.Expense.ParameterValue.TypeParameter();
					typePara.setId(parameterValue.getTypeParameter().getId());
					typePara.setValue(parameterValue.getTypeParameter()
							.getValue());
					value.setId(parameterValue.getId());
					value.setTypeParameter(typePara);
					value.setValue(parameterValue.getValue());
					e.getParameterValues().add(value);
				}
				ret.getExpenses().add(e);
			}
		}

		return ret;
	}

	private ReportSummarySendto toReportSummarySendto(Report report,
			boolean detail) {
		ReportSummarySendto ret = new ReportSummarySendto();
		ret.setId(report.getId());
		ret.setAttendanceRecordId(report.getAttendanceRecordId());
		ret.setCreatedDate(report.getCreatedDate());
		ret.setEndDate(report.getEndDate());
		ret.setRoute(report.getRoute());
		ret.setStartDate(report.getStartDate());
		ret.setCurrentStatus(report.getCurrentStatus().name());
		ReportSummarySendto.User usr = new ReportSummarySendto.User();
		usr.setId(report.getOwner().getId());
		Employee owner = employeeDao.findOne(report.getOwner()
				.getUserSharedId());
		String name = owner.getName();
		usr.setName(name);
		ret.setOwner(usr);
		ret.setFirmOrProject(report.getFirmOrProject());

		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			Report report = reportDao.findOne(id);
			if (report == null) {
				throw new ReportNotFoundException(id);
			}
			reportDao.delete(report);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ReportNotFoundException(id);
		}
	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto save(ReportSendto report) {
		report.setId(null);
		Report newEntry = new Report();
		setUpReport(report, newEntry);
		if (report.getAttendanceRecordId() == null) {
			throw new BadRequestException("invalid attendRecordId");
		}
		if (!report.isOwnerSet() || !report.getOwner().isIdSet()) {
			throw new BadRequestException("invalid ownerId");
		}
		AttendRecordSpecification recordSpec = new AttendRecordSpecification();
		recordSpec.setApplicantId(report.getOwner().getId());
		recordSpec.setId(report.getAttendanceRecordId());
		AttendRecord record = recordDao.findOne(recordSpec);
		if (record == null) {
			throw new AttendRecordNotFoundException(
					report.getAttendanceRecordId());
		}
		newEntry.setStartDate(record.getStartDate());
		newEntry.setEndDate(record.getEndDate());
		newEntry.setCreatedDate(new Date());
		newEntry.setMaxIdLastMonth(getMaxIdLastMonth());
		newEntry.setCurrentStatus(StatusEnum.SUBMITTED);

		Report ret = reportDao.save(newEntry);

		if (report.getExpenses() != null) {
			for (sendto.ReportSendto.Expense e : report.getExpenses()) {
				e.setTaxAmount(null);
				e.setTaxAmountSet(false);
			}
			addExpenses(ret, report.getExpenses());
		}

		sendMailToAllAccountant(ret);
		return toReportSendto(ret, true);
	}

	private Long getMaxIdLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MILLISECOND, -1);
		Date lastMonthEnd = cal.getTime();
		Report report = reportDao
				.findTopByCreatedDateBeforeOrderByIdDesc(lastMonthEnd);

		Long maxIdLastMonth = report != null ? report.getId() : null;

		logger.debug("MaxIdLastMonth has been selected: {}, before date: {}",
				new Object[] { maxIdLastMonth, lastMonthEnd });
		return maxIdLastMonth == null ? 0 : maxIdLastMonth;
	}

	@Transactional("transactionManager")
	@Override
	public Page<ReportSummarySendto> findAll(ReportSpecification spec,
			Pageable pageable) {
		List<ReportSummarySendto> sendto = new ArrayList<ReportSummarySendto>();
		if (!Strings.isNullOrEmpty(spec.getApplicantName())) {
			EmployeeSpecification employeeSpec = new EmployeeSpecification();
			employeeSpec.setName(spec.getApplicantName());
			List<Employee> employees = employeeDao.findAll(employeeSpec);
			List<Long> ids = new ArrayList<Long>();
			for (Employee e : employees) {
				ids.add(e.getId());
			}
			spec.setIds(ids);
		}
		Page<Report> reports = reportDao.findAll(spec, pageable);

		for (Report report : reports) {
			sendto.add(toReportSummarySendto(report, false));
		}
		Page<ReportSummarySendto> rets = new PageImpl<ReportSummarySendto>(
				sendto, pageable, reports.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto update(Specification<Report> spec,
			ReportSendto updated, long currentUserId) {
		Report rpt = reportDao.findOne(spec);
		if (rpt == null) {
			throw new ReportNotFoundException();
		}
		User currentUser = userDao.findOne(currentUserId);
		if (currentUser == null) {
			throw new UserNotFoundException(currentUserId);
		}
		return update(rpt, updated, currentUser);
	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto update(long id, ReportSendto updated,
			String currentUsername) {
		Report rpt = reportDao.findOne(id);
		if (rpt == null) {
			throw new ReportNotFoundException();
		}
		User currentUser = this.findCurrentUser(currentUsername);
		if (currentUser == null) {
			throw new UserNotFoundException(currentUsername);
		}
		return update(rpt, updated, currentUser);
	}

	private ReportSendto update(Report rpt, ReportSendto updated,
			User currentUser) {
		rpt.setLastUpdatedDate(new Date());
		setUpReport(updated, rpt);
		boolean resubmitted = false;
		if (currentUser.equals(rpt.getOwner())
				&& !currentUser.equals(rpt.getReviewer())) {
			if (rpt.getCurrentStatus() == StatusEnum.REJECTED) {
				resubmitted = resubmit(rpt, currentUser);
			} else if (rpt.getCurrentStatus() == StatusEnum.APPROVED) {
				resubmitted = resubmit(rpt, currentUser);
			}
		}
		rpt = reportDao.save(rpt);

		Map<Long, Expense> expenseMap = new HashMap<Long, Expense>();
		Set<ReportSendto.Expense> added = new HashSet<ReportSendto.Expense>();
		Map<Long, ReportSendto.Expense> updatedExpense = new HashMap<Long, ReportSendto.Expense>();
		for (Expense expense : rpt.getExpenses()) {
			expenseMap.put(expense.getId(), expense);
		}

		for (ReportSendto.Expense e : updated.getExpenses()) {
			if (e.getId() == null) {
				added.add(e);
				continue;
			}
			if (!expenseMap.containsKey(e.getId())) {
				throw new BadRequestException("invalid expense:" + e.getId());
			}
			updatedExpense.put(e.getId(), e);
		}

		for (ReportSendto.Expense e : updatedExpense.values()) {
			Expense old = expenseMap.get(e.getId());
			if (e.isExpenseTypeSet() && e.getExpenseType().isIdSet()) {
				// expenseType changed is not allowed
				if (old.getExpenseType().getId() != e.getExpenseType().getId()) {
					throw new BadRequestException(
							"expenseType cannot be change:" + e.getId());
				}
				List<sendto.ReportSendto.Expense.ParameterValue> parameterValues = e
						.getParameterValues();
				if (parameterValues == null
						|| old.getExpenseType().getExpenseTypePara().size() != parameterValues
								.size()) {
					throw new BadRequestException(
							"invalid parameterValues size");
				}

				Map<Long, sendto.ReportSendto.Expense.ParameterValue> updatedMap = new HashMap<Long, sendto.ReportSendto.Expense.ParameterValue>();
				for (sendto.ReportSendto.Expense.ParameterValue v : parameterValues) {
					updatedMap.put(v.getId(), v);
				}

				for (ParameterValue v : old.getParameterValue()) {
					sendto.ReportSendto.Expense.ParameterValue newValue = updatedMap
							.get(v.getId());
					if (newValue == null) {
						throw new BadRequestException("invalid parameterValue:"
								+ v.getId());
					}
					v.setValue(newValue.getValue());
					parameterValueDao.save(v);
				}

				if (e.isTaxAmountSet()) {
					old.setTaxAmount(e.getTaxAmount());
				}
				expenseDao.save(old);
			} else {
				throw new BadRequestException("invalid expense:" + e.getId());
			}
		}

		Set<Long> removed = Sets.difference(expenseMap.keySet(),
				updatedExpense.keySet());
		for (Long idRemove : removed) {
			rpt.getExpenses().remove(expenseMap.get(idRemove));
			expenseDao.delete(idRemove);
		}
		addExpenses(rpt, added);

		if (resubmitted) {
			sendMailToAllAccountant(rpt);
		}

		return toReportSendto(rpt, true);
	}

	private void addExpenses(Report rpt,
			Collection<ReportSendto.Expense> expenses) {
		for (ReportSendto.Expense expense : expenses) {
			Expense e = new Expense();
			if (expense.isCommentSet()) {
				e.setComment(expense.getComment());
			}
			if (expense.isTotalAmountSet()) {
				e.setTotalAmount(expense.getTotalAmount());
			} else {
				throw new BadRequestException("invalid total amount");
			}

			if (expense.isExpenseTypeSet()
					|| expense.getExpenseType().isIdSet()) {
				ExpenseType expenseType = expenseTypeDao.findOne(expense
						.getExpenseType().getId());
				e.setExpenseType(expenseType);

				Set<ExpenseTypePara> expenseTypeParas = expenseType
						.getExpenseTypePara();
				List<sendto.ReportSendto.Expense.ParameterValue> parameterValues = expense
						.getParameterValues();
				if (parameterValues == null
						|| expenseTypeParas.size() != parameterValues.size()) {
					throw new BadRequestException(
							"invalid parameterValues size");
				}
				for (ExpenseTypePara para : expenseTypeParas) {
					Long typeParaId = para.getTypeParameter().getId();
					ParameterValue parameterValue = null;
					for (sendto.ReportSendto.Expense.ParameterValue pValue : parameterValues) {
						if (pValue.isTypeParameterSet()
								&& pValue.getTypeParameter().isIdSet()) {
							if (typeParaId.equals(pValue.getTypeParameter()
									.getId())) {
								parameterValue = new ParameterValue();
								parameterValue.setTypeParameter(para
										.getTypeParameter());
								parameterValue.setExpense(e);
								if (!pValue.isValueSet()) {
									throw new BadRequestException(
											"invalid parameterValue");
								}
								parameterValue.setValue(pValue.getValue());
								parameterValueDao.save(parameterValue);
								para.getTypeParameter().getParameterValues()
										.add(parameterValue);
								e.getParameterValue().add(parameterValue);
							}
						} else {
							throw new BadRequestException(
									"invalid parameterValue");
						}
					}
					// no parameterType match
					if (parameterValue == null) {
						throw new BadRequestException("invalid parameterValues");
					}
				}
			} else {
				throw new BadRequestException("invalid expenseType's id");
			}

			if (expense.isTaxAmountSet()) {
				e.setTaxAmount(expense.getTaxAmount());
			} else {
				Double taxPercent = e.getExpenseType().getTaxPercent();
				Integer totalAmount = expense.getTotalAmount();
				Integer tax = new BigDecimal(totalAmount * taxPercent / 100)
						.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				e.setTaxAmount(tax);
			}
			e.setReport(rpt);
			e = expenseDao.save(e);
			rpt.getExpenses().add(e);
		}
	}

	@Override
	@Transactional("transactionManager")
	public ReportSendto approve(long id, StatusChangeSendto statusChange,
			long currentUserId) {
		Report rpt = reportDao.findOne(id);
		if (rpt == null) {
			throw new ReportNotFoundException(id);
		}
		User currentUser = userDao.findOne(currentUserId);
		if (currentUser == null) {
			throw new UserNotFoundException(currentUserId);
		}
		rpt.setLastUpdatedDate(new Date());
		changeStatus(rpt, currentUser, StatusEnum.APPROVED,
				statusChange.getComment());
		logger.info("Report (id = {0}) has been approved by {1}", rpt.getId(),
				currentUser.getId());
		rpt = reportDao.save(rpt);
		sendMailToOwner(rpt);
		return toReportSendto(rpt, true);
	}

	@Override
	@Transactional("transactionManager")
	public ReportSendto reject(long id, StatusChangeSendto statusChange,
			long currentUserId) {
		Report rpt = reportDao.findOne(id);
		if (rpt == null) {
			throw new ReportNotFoundException(id);
		}
		User currentUser = userDao.findOne(currentUserId);
		if (currentUser == null) {
			throw new UserNotFoundException(currentUserId);
		}

		rpt.setLastUpdatedDate(new Date());
		changeStatus(rpt, currentUser, StatusEnum.REJECTED,
				statusChange.getComment());
		logger.info("Report (id = {0}) has been rejected by {1}", rpt.getId(),
				currentUser.getId());
		rpt = reportDao.save(rpt);
		sendMailToOwner(rpt);
		return toReportSendto(rpt, true);
	}

	private void sendMailToAllAccountant(Report report) {
		try {
			String subject = writter.buildSubmittedSubject(report);
			String body = writter.buildSubmittedBody(report);
			List<User> accountants = getAccountants();
			for (User accountant : accountants) {
				Employee receiver = employeeDao.findOne(accountant
						.getUserSharedId());
				mailService.sendMail(receiver.getEmail(), subject, body);
			}
		} catch (Throwable t) {
			logger.warn("send mail failed, ignore", t);
		}
	}

	private void sendMailToOwner(Report report) {
		try {
			String subject = writter.buildReviewedSubject(report);
			String body = writter.buildReviewedBody(report);
			Employee receiver = employeeDao.findOne(report.getOwner()
					.getUserSharedId());
			mailService.sendMail(receiver.getEmail(), subject, body);
		} catch (Throwable t) {
			logger.warn("send mail failed, ignore", t);
		}
	}

	private User findCurrentUser(String currentUsername) {
		UserSpecification userSpec = new UserSpecification();
		userSpec.setUsername(currentUsername);
		Employee employee = employeeDao.findByUsername(currentUsername);
		User currentUser = userDao.findByUserSharedId(employee.getId());
		return currentUser;
	}

	private boolean resubmit(Report report, User user) {
		changeStatus(report, user, StatusEnum.SUBMITTED, "resubmitted");
		return true;
	}

	private void changeStatus(Report report, User user, StatusEnum status,
			String comment) {
		logger.info("Changing status of Report({0}) to {1}, with comment {2}",
				new Object[] { report, status, comment });

		StatusChange statusChange = new StatusChange(user, status, report,
				comment, new Date());
		statusChangeDao.save(statusChange);

		report.getStatusChanges().add(statusChange);
		report.setCurrentStatus(status);
		report.setReviewer(user);
	}

	private void setUpReport(ReportSendto sendto, Report newEntry) {
		if (sendto.isAttendanceRecordIdSet()) {
			newEntry.setAttendanceRecordId(sendto.getAttendanceRecordId());
		}
		if (sendto.isOwnerSet()) {
			if (sendto.getOwner().isIdSet()) {
				entity.User user = userDao.findOne(sendto.getOwner().getId());
				if (user == null) {
					throw new UserNotFoundException(sendto.getOwner().getId());
				}
				newEntry.setOwner(user);
			}
		}

		if (sendto.isReasonSet()) {
			newEntry.setReason(sendto.getReason());
		}
		if (sendto.isRouteSet()) {
			newEntry.setRoute(sendto.getRoute());
		}
		if (sendto.isStartDateSet()) {
			newEntry.setStartDate(sendto.getStartDate());
		}
		if (sendto.isEndDateSet()) {
			newEntry.setEndDate(sendto.getEndDate());
		}
		if (sendto.isCommentSet()) {
			newEntry.setComment(sendto.getComment());
		}
		if (sendto.isCreatedDateSet()) {
			newEntry.setCreatedDate(sendto.getCreatedDate());
		}
		if (sendto.isLastUpdatesDateSet()) {
			newEntry.setLastUpdatedDate(sendto.getLastUpdatedDate());
		}
		if (sendto.isCurrentStatusSet()) {
			newEntry.setCurrentStatus(StatusEnum.valueOf(sendto
					.getCurrentStatus()));
		}

		if (sendto.isFirmOrProjectSet()) {
			newEntry.setFirmOrProject(sendto.getFirmOrProject());
		}

	}

	private List<User> getAccountants() {
		UserRoleSpecification spec = new UserRoleSpecification();
		spec.setRoleName(RoleEnum.ACCOUNTANT);
		List<UserRole> userRoles = userRoleDao.findAll(spec);
		List<User> accountants = new ArrayList<User>();

		for (UserRole userRole : userRoles) {
			User accountant = userRole.getUser();
			accountants.add(accountant);
		}
		return accountants;
	}

	@Transactional("transactionManager")
	@Override
	public ReportSendto retrieve(Specification<Report> spec) {
		Report rpt = reportDao.findOne(spec);
		if (rpt == null) {
			throw new ReportNotFoundException();
		}
		return toReportSendto(rpt, true);
	}

	@Transactional("transactionManager")
	@Override
	public void delete(Specification<Report> spec) {
		Report rpt = reportDao.findOne(spec);
		if (rpt == null) {
			throw new ReportNotFoundException();
		}
		reportDao.delete(rpt);
	}

}
