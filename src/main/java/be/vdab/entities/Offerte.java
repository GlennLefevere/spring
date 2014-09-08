package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.enums.Gazontype;

@Embeddable
public class Offerte implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface Stap1 {
	}

	public interface Stap2 {
	}

	@Transient
	private Map<Gazontype, Boolean> gazontypes = new LinkedHashMap<>();
	@Transient
	private List<String> telefoonNrs = new ArrayList<>();
	@NotNull(groups = Stap1.class)
	@NotBlank(groups = Stap1.class)
	@SafeHtml
	private String voornaam;
	@NotNull(groups = Stap1.class)
	@NotBlank(groups = Stap1.class)
	@SafeHtml
	private String familienaam;
	@NotNull(groups = Stap1.class)
	@Email(groups = Stap1.class)
	private String emailAdres;
	@NotNull(groups = Stap2.class)
	@Min(value = 1, groups = Stap2.class)
	private Integer oppervlakte;

	public Offerte() {
		for (Gazontype gazontype : Gazontype.values()) {
			gazontypes.put(gazontype, false);
		}
		telefoonNrs.add(""); // vak voor een eerste te tikken telefoonnummer
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public Integer getOppervlakte() {
		return oppervlakte;
	}

	public void setOppervlakte(Integer oppervlakte) {
		this.oppervlakte = oppervlakte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getTelefoonNrs() {
		return telefoonNrs;
	}

	public void setTelefoonNrs(List<String> telefoonNrs) {
		this.telefoonNrs = telefoonNrs;
	}

	public void nogEenTelefoonNr() {
		telefoonNrs.add("");
	}

	public Map<Gazontype, Boolean> getGazontypes() {
		return gazontypes;
	}

	public void setGazontypes(Map<Gazontype, Boolean> gazontypes) {
		this.gazontypes = gazontypes;
	}
}
