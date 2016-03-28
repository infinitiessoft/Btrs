package sendto;

import javax.xml.bind.annotation.XmlTransient;

import com.google.common.base.Strings;

public class ExpenseCategorySendto {

	private Long id;
	private String name_key;
	private String code;

	public ExpenseCategorySendto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName_key() {
		return name_key;
	}

	public void setName_key(String name_key) {
		if (!Strings.isNullOrEmpty(name_key)) {
			isName_keySet = true;
		}
		this.name_key = name_key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (!Strings.isNullOrEmpty(code)) {
			isCodeSet = true;
		}
		this.code = code;
	}

	private boolean isName_keySet;
	private boolean isCodeSet;

	@XmlTransient
	public boolean isName_keySet() {
		return isName_keySet;
	}

	@XmlTransient
	public void setName_keySet(boolean isName_keySet) {
		this.isName_keySet = isName_keySet;
	}

	@XmlTransient
	public boolean isCodeSet() {
		return isCodeSet;
	}

	@XmlTransient
	public void setCodeSet(boolean isCodeSet) {
		this.isCodeSet = isCodeSet;
	}

}
