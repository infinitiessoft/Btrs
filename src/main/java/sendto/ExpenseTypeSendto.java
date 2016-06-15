package sendto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class ExpenseTypeSendto {

	public static class TypeParameter {

		private Long id;
		private String value;
		private String dataType;
		private boolean isIdSet = false;
		private boolean isValueSet;
		private boolean isDataTypeSet;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				isIdSet = true;
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

		@XmlTransient
		public boolean isValueSet() {
			return isValueSet;
		}

		@XmlTransient
		public void setValueSet(boolean isValueSet) {
			this.isValueSet = isValueSet;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			if (!Strings.isNullOrEmpty(dataType)) {
				isDataTypeSet = true;
			}
			this.dataType = dataType;
		}

		@XmlTransient
		public boolean isDataTypeSet() {
			return isDataTypeSet;
		}

		@XmlTransient
		public void setDataTypeSet(boolean isDataTypeSet) {
			this.isDataTypeSet = isDataTypeSet;
		}

	}

	public static class ExpenseCategory {

		private Long id;
		private String name;
		private boolean isNameSet = false;
		private boolean isIdSet = false;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				isIdSet = true;
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			if (!Strings.isNullOrEmpty(name)) {
				isIdSet = true;
			}
			this.name = name;
		}

		@XmlTransient
		public boolean isNameSet() {
			return isNameSet;
		}

		@XmlTransient
		public void setNameSet(boolean isNameSet) {
			this.isNameSet = isNameSet;
		}

	}

	private Long id;
	private Double taxPercent;
	private String value;
	private ExpenseCategory expenseCategory;
	private List<TypeParameter> typeParameters = new ArrayList<TypeParameter>(0);

	private boolean isTaxPercentSet;
	private boolean isValueSet;
	private boolean isExpenseCategorySet;

	public ExpenseTypeSendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		if (taxPercent != null) {
			isTaxPercentSet = true;
		}
		this.taxPercent = taxPercent;
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

	@XmlTransient
	public boolean isTaxPercentSet() {
		return isTaxPercentSet;
	}

	@XmlTransient
	public void setTaxPercentSet(boolean isTaxPercentSet) {
		this.isTaxPercentSet = isTaxPercentSet;
	}

	@XmlTransient
	public boolean isValueSet() {
		return isValueSet;
	}

	@XmlTransient
	public void setValueSet(boolean isValueSet) {
		this.isValueSet = isValueSet;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		if (expenseCategory != null) {
			isExpenseCategorySet = true;
		}
		this.expenseCategory = expenseCategory;
	}

	@XmlTransient
	public boolean isExpenseCategorySet() {
		return isExpenseCategorySet;
	}

	@XmlTransient
	public void setExpenseCategorySet(boolean isExpenseCategorySet) {
		this.isExpenseCategorySet = isExpenseCategorySet;
	}

	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	public void setTypeParameters(List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
	}

}
