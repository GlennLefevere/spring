package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;

@Controller
@RequestMapping(value = "/filialen", produces = MediaType.TEXT_HTML_VALUE)
class FiliaalController {
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	private static final String VERWIJDERD_VIEW = "filialen/verwijderd";
	private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";
	private final FiliaalService filiaalService;

	@Autowired
	FiliaalController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll()).addObject("aantalFilialen", filiaalService.findAantalFilialen());
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "filiaal", new Filiaal());
	}

	@RequestMapping(method = RequestMethod.POST)
	String create(@Valid @ModelAttribute("filiaal") Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		filiaalService.create(filiaal);
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	public FiliaalService getFiliaalService() {
		return filiaalService;
	}

	@RequestMapping(value = "{filiaal}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Filiaal filiaal) {
		return new ModelAndView(FILIAAL_VIEW, "filiaal", filiaal);
	}

	@RequestMapping(value = "{filiaal}", method = RequestMethod.DELETE)
	String delete(@PathVariable Filiaal filiaal, RedirectAttributes redirectAttributes) {
		if (filiaal == null) {
			return REDIRECT_URL_FILIAAL_NIET_GEVONDEN;
		}
		try {
			filiaalService.delete(filiaal.getId());
			redirectAttributes.addAttribute("id", filiaal.getId()).addAttribute("naam", filiaal.getNaam());
			return REDIRECT_URL_NA_VERWIJDEREN;
		} catch (FiliaalHeeftNogWerknemersException ex) {
			redirectAttributes.addAttribute("id", filiaal.getId()).addAttribute("fout", "Filiaal heeft nog werknemers");
			return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
		}
	}

	@RequestMapping(value = "{id}/verwijderd", method = RequestMethod.GET)
	ModelAndView deleted(@PathVariable long id, String naam) {
		//System.out.println(naam);
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}

	@RequestMapping(value = "perpostcode", method = RequestMethod.GET)
	ModelAndView findByPostcodeForm() {
		PostcodeReeks reeks = new PostcodeReeks();
		return new ModelAndView(PER_POSTCODE_VIEW, "reeks", reeks);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "vanpostcode", "totpostcode" })
	ModelAndView findByPostcodeReeks(@Valid @ModelAttribute("reeks") PostcodeReeks reeks, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PER_POSTCODE_VIEW);
		if (!bindingResult.hasErrors() && !reeks.isValid()) {
			bindingResult.reject("foutePostcodeReeks", new Object[] { reeks.getVanpostcode(), reeks.getTotpostcode() }, "");
		}
		if (!bindingResult.hasErrors()) {
			modelAndView.addObject("filialen", filiaalService.findByPostcodeReeks(reeks));
		}
		return modelAndView;
	}

	@RequestMapping(value = "{filiaal}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Filiaal filiaal) {
		if (filiaal == null) {
			return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
		}
		return new ModelAndView(WIJZIGEN_VIEW, "filiaal", filiaal);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	String update(@Valid @ModelAttribute("filiaal") Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		filiaalService.update(filiaal);
		return REDIRECT_URL_NA_WIJZIGEN;
	}
}
