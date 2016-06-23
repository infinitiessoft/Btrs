package sendto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class ReportSendto {

	private Long id;

	private Long maxIdLastMonth;

	private Long attendanceRecordId;

	private String reason;

	private String route;

	private Date startDate;

	private Date endDate;

	private String comment;

	private Date createdDate;

	private Date lastUpdatedDate;

	private User owner;

	private User reviewer;

	private String currentStatus;

	private List<Expense> expenses = new ArrayList<Expense>(0);

	private boolean isReasonSet;
	private boolean isRouteSet;
	private boolean isStartDateSet;
	private boolean isEndDateSet;
	private boolean isCommentSet;
	private boolean isCreatedDateSet;
	private boolean isLastUpdatesDateSet;
	private boolean isOwnerSet;
	private boolean isReviewerSet;
	private boolean isCurrentStatusSet;
	private boolean isMaxIdLastMonthSet;
	private boolean isAttendanceRecordIdSet;

	public ReportSendto() {
		super();
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		if (owner != null) {
			isOwnerSet = true;
		}
		this.owner = owner;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		if (reviewer != null) {
			isReviewerSet = true;
		}
		this.reviewer = reviewer;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		if (!Strings.isNullOrEmpty(currentStatus)) {
			isCurrentStatusSet = true;
		}
		this.currentStatus = currentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaxIdLastMonth() {
		return maxIdLastMonth;
	}

	public void setMaxIdLastMonth(Long maxIdLastMonth) {
		if (maxIdLastMonth != null) {
			isMaxIdLastMonthSet = true;
		}
		this.maxIdLastMonth = maxIdLastMonth;
	}

	public Long getAttendanceRecordId() {
		return attendanceRecordId;
	}

	public void setAttendanceRecordId(Long attendanceRecordId) {
		if (attendanceRecordId != null) {
			isAttendanceRecordIdSet = true;
		}
		this.attendanceRecordId = attendanceRecordId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		if (!Strings.isNullOrEmpty(reason)) {
			isReasonSet = true;
		}
		this.reason = reason;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		if (!Strings.isNullOrEmpty(route)) {
			isRouteSet = true;
		}
		this.route = route;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			isStartDateSet = true;
		}
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (startDate != null) {
			isStartDateSet = true;
		}
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (!Strings.isNullOrEmpty(comment)) {
			isCommentSet = true;
		}
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		if (createdDate != null) {
			isCreatedDateSet = true;
		}
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		if (lastUpdatedDate != null) {
			isLastUpdatesDateSet = true;
		}
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@XmlTransient
	public boolean isReasonSet() {
		return isReasonSet;
	}

	@XmlTransient
	public void setReasonSet(boolean isReasonSet) {
		this.isReasonSet = isReasonSet;
	}

	@XmlTransient
	public boolean isRouteSet() {
		return isRouteSet;
	}

	@XmlTransient
	public void setRouteSet(boolean isRouteSet) {
		this.isRouteSet = isRouteSet;
	}

	@XmlTransient
	public boolean isStartDateSet() {
		return isStartDateSet;
	}

	@XmlTransient
	public void setStartDateSet(boolean isStartDateSet) {
		this.isStartDateSet = isStartDateSet;
	}

	@XmlTransient
	public boolean isEndDateSet() {
		return isEndDateSet;
	}

	@XmlTransient
	public void setEndDateSet(boolean isEndDateSet) {
		this.isEndDateSet = isEndDateSet;
	}

	@XmlTransient
	public boolean isCommentSet() {
		return isCommentSet;
	}

	@XmlTransient
	public void setCommentSet(boolean isCommentSet) {
		this.isCommentSet = isCommentSet;
	}

	@XmlTransient
	public boolean isCreatedDateSet() {
		return isCreatedDateSet;
	}

	@XmlTransient
	public void setCreatedDateSet(boolean isCreatedDateSet) {
		this.isCreatedDateSet = isCreatedDateSet;
	}

	@XmlTransient
	public boolean isLastUpdatesDateSet() {
		return isLastUpdatesDateSet;
	}

	@XmlTransient
	public void setLastUpdatesDateSet(boolean isLastUpdatesDateSet) {
		this.isLastUpdatesDateSet = isLastUpdatesDateSet;
	}

	@XmlTransient
	public boolean isOwnerSet() {
		return isOwnerSet;
	}

	@XmlTransient
	public void setOwnerSet(boolean isOwnerSet) {
		this.isOwnerSet = isOwnerSet;
	}

	@XmlTransient
	public boolean isReviewerSet() {
		return isReviewerSet;
	}

	@XmlTransient
	public void setReviewerSet(boolean isReviewerSet) {
		this.isReviewerSet = isReviewerSet;
	}

	@XmlTransient
	public boolean isCurrentStatusSet() {
		return isCurrentStatusSet;
	}

	@XmlTransient
	public void setCurrentStatusSet(boolean isCurrentStatusSet) {
		this.isCurrentStatusSet = isCurrentStatusSet;
	}

	@XmlTransient
	public boolean isMaxIdLastMonthSet() {
		return isMaxIdLastMonthSet;
	}

	@XmlTransient
	public void setMaxIdLastMonthSet(boolean isMaxIdLastMonthSet) {
		this.isMaxIdLastMonthSet = isMaxIdLastMonthSet;
	}

	@XmlTransient
	public boolean isAttendanceRecordIdSet() {
		return isAttendanceRecordIdSet;
	}

	@XmlTransient
	public void setAttendanceRecordIdSet(boolean isAttendanceRecordIdSet) {
		this.isAttendanceRecordIdSet = isAttendanceRecordIdSet;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public static class Expense implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ExpenseType expenseType;
		private List<ParameterValue> parameterValues = new ArrayList<ParameterValue>(
				0);
		private Long id;
		private String comment;

		private boolean isIdSet;
		private boolean isCommentSet;
		private boolean isExpenseTypeSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				setIdSet(true);
			}
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}

		public ExpenseType getExpenseType() {
			return expenseType;
		}

		public void setExpenseType(ExpenseType expenseType) {
			if (expenseType != null) {
				isExpenseTypeSet = true;
			}
			this.expenseType = expenseType;
		}

		public List<ParameterValue> getParameterValues() {
			return parameterValues;
		}

		public void setParameterValues(List<ParameterValue> parameterValues) {
			this.parameterValues = parameterValues;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			isCommentSet = true;
			this.comment = comment;
		}

		@XmlTransient
		public boolean isCommentSet() {
			return isCommentSet;
		}

		@XmlTransient
		public void setCommentSet(boolean isCommentSet) {
			this.isCommentSet = isCommentSet;
		}

		@XmlTransient
		public boolean isExpenseTypeSet() {
			return isExpenseTypeSet;
		}

		@XmlTransient
		public void setExpenseTypeSet(boolean isExpenseTypeSet) {
			this.isExpenseTypeSet = isExpenseTypeSet;
		}

		public static class ExpenseType implements Serializable {
			/**
			 * 
			 */

			private static final long serialVersionUID = 1L;
			private Long id;
			private String value;

			private boolean isIdSet;

			public ExpenseType() {
				super();
			}

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				if (id != null) {
					setIdSet(true);
				}
				this.id = id;
			}

			@XmlTransient
			public boolean isIdSet() {
				return isIdSet;
			}

			@XmlTransient
			public void setIdSet(boolean isIdSet) {
				this.isIdSet = isIdSet;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

		}

		public static class ParameterValue implements Serializable {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private TypeParameter typeParameter;
			private Long id;
			private String value;

			private boolean isTypeParameterSet;
			private boolean isValueSet;
			private boolean isIdSet;

			public TypeParameter getTypeParameter() {
				return typeParameter;
			}

			public void setTypeParameter(TypeParameter typeParameter) {
				if (typeParameter != null) {
					isTypeParameterSet = true;
				}
				this.typeParameter = typeParameter;
			}
			
			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				if (id != null) {
					setIdSet(true);
				}
				this.id = id;
			}

			@XmlTransient
			public boolean isIdSet() {
				return isIdSet;
			}

			@XmlTransient
			public void setIdSet(boolean isIdSet) {
				this.isIdSet = isIdSet;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				if (!Strings.isNullOrEmpty(value)) {
					isValueSet = true;
				}
				this.value = value;
			}

			public boolean isTypeParameterSet() {
				return isTypeParameterSet;
			}

			public void setTypeParameterSet(boolean isTypeParameterSet) {
				this.isTypeParameterSet = isTypeParameterSet;
			}

			public boolean isValueSet() {
				return isValueSet;
			}

			public void setValueSet(boolean isValueSet) {
				this.isValueSet = isValueSet;
			}

			public static class TypeParameter implements Serializable {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				private Long id;
				private String value;
				private String dataType;

				private boolean isIdSet;

				public TypeParameter() {
					super();
				}

				public Long getId() {
					return id;
				}

				public void setId(Long id) {
					if (id != null) {
						setIdSet(true);
					}
					this.id = id;
				}

				@XmlTransient
				public boolean isIdSet() {
					return isIdSet;
				}

				@XmlTransient
				public void setIdSet(boolean isIdSet) {
					this.isIdSet = isIdSet;
				}

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public String getDataType() {
					return dataType;
				}

				public void setDataType(String dataType) {
					this.dataType = dataType;
				}
			}

		}

	}

	public static class User implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;

		private boolean isIdSet;

		public User() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			setIdSet(true);
			this.id = id;
		}

		@XmlTransient
		public boolean isIdSet() {
			return isIdSet;
		}

		@XmlTransient
		public void setIdSet(boolean isIdSet) {
			this.isIdSet = isIdSet;
		}

	}

}
