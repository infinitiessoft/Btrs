package resources.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import util.DateUtils;

import com.google.common.base.Strings;

import entity.Report;
import entity.StatusEnum;
import entity.User;

public class ReportSpecification implements Specification<Report> {

	// @QueryParam("firstName")
	// private String firstName;
	@QueryParam("applicantName")
	private String applicantName;
	private Long applicantId;
	@QueryParam("endDate")
	private String endDate;
	@QueryParam("createdDate")
	private String createdDate;
	@QueryParam("startDate")
	private String startDate;
	@QueryParam("currentStatus")
	private StatusEnum currentStatus;
	@QueryParam("reason")
	private String reason;
	@QueryParam("route")
	private String route;
	@QueryParam("firmOrProject")
	private String firmOrProject;

	private Long id;
	private List<Long> ids;

	@Override
	public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (currentStatus != null) {
			predicates.add(cb.equal(root.<StatusEnum> get("currentStatus"),
					currentStatus));
		}
		if (!Strings.isNullOrEmpty(reason)) {
			predicates.add(cb.like(root.<String> get("reason"), "%" + reason
					+ "%"));
		}
		if (!Strings.isNullOrEmpty(route)) {
			predicates.add(cb.like(root.<String> get("route"), "%" + route
					+ "%"));
		}
		if (!Strings.isNullOrEmpty(firmOrProject)) {
			predicates.add(cb.like(root.<String> get("firmOrProject"), "%" + firmOrProject
					+ "%"));
		}
		if (!Strings.isNullOrEmpty(startDate)) {
			Date date = DateUtils.parseString(startDate);
			if (date != null) {
				predicates.add(cb.greaterThanOrEqualTo(
						root.<Date> get("startDate"), date));
			}
		}
		if (!Strings.isNullOrEmpty(endDate)) {
			Date date = DateUtils.parseString(endDate);
			if (date != null) {
				predicates.add(cb.lessThanOrEqualTo(root.<Date> get("endDate"),
						date));
			}
		}
		if (!Strings.isNullOrEmpty(createdDate)) {
			Date date = DateUtils.parseString(createdDate);
			if (date != null) {
				predicates.add(cb.greaterThanOrEqualTo(
						root.<Date> get("createdDate"), date));
			}
		}

		if (applicantId != null) {
			predicates.add(cb.equal(root.<User> get("owner").<Long> get("id"),
					applicantId));
		}

		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}
		if (ids != null) {
			predicates.add(root.<User> get("owner").<Long> get("id").in(ids));
		}

		// if (applicantName != null) {
		// predicates.add(cb.like(root.<User> get("owner")
		// .<String> get("name"), "%" + applicantName + "%"));
		// }

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusEnum getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(StatusEnum currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
