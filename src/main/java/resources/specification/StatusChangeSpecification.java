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
import entity.StatusChange;
import entity.User;

public class StatusChangeSpecification implements Specification<StatusChange> {

	@QueryParam("comment")
	private String comment;
	@QueryParam("value")
	private String value;
	@QueryParam("userId")
	private Long userId;
	@QueryParam("reportId")
	private Long reportId;
	@QueryParam("createdDate")
	private String createdDate;

	private Long id;

	@Override
	public Predicate toPredicate(Root<StatusChange> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!Strings.isNullOrEmpty(comment)) {
			predicates.add(cb.like(root.<String> get("comment"), "%" + comment
					+ "%"));
		}
		if (!Strings.isNullOrEmpty(value)) {
			predicates.add(cb.like(root.<String> get("value"), "%" + comment
					+ "%"));
		}

		if (!Strings.isNullOrEmpty(createdDate)) {
			Date date = DateUtils.parseString(createdDate);
			if (date != null) {
				predicates.add(cb.greaterThanOrEqualTo(
						root.<Date> get("createdDate"), date));
			}
		}

		if (userId != null) {
			predicates.add(cb.equal(root.<User> get("user").<Long> get("id"),
					userId));
		}

		if (reportId != null) {
			predicates.add(cb.equal(root.<Report> get("report")
					.<Long> get("id"), reportId));
		}

		if (id != null) {
			predicates.add(cb.equal(root.<Long> get("id"), id));
		}

		if (predicates.isEmpty()) {
			return null;
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
