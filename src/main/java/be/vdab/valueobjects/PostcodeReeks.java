package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;

public class PostcodeReeks {
	@NotNull 
	@Postcode
	private Integer vanpostcode;
	@NotNull 
	@Postcode
	private Integer totpostcode;

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public void setVanpostcode(int vanpostcode) {
		this.vanpostcode = vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setTotpostcode(int totpostcode) {
		this.totpostcode = totpostcode;
	}

	public boolean bevat(int postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}


	public boolean isValid() {
		return vanpostcode != null && totpostcode != null && vanpostcode <= totpostcode;
	}
}
