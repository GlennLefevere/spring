package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.commons.Common;
import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.mail.MailSender;
import be.vdab.valueobjects.PostcodeReeks;

@Service
@Transactional(readOnly = true)
class FiliaalServiceImpl implements FiliaalService {
	private final FiliaalDAO filiaalDAO;
	private final MailSender mailSender;
	private final Common common;

	@Autowired
	FiliaalServiceImpl(FiliaalDAO filiaalDAO, MailSender mailSender, Common common) {
		this.filiaalDAO = filiaalDAO;
		this.mailSender = mailSender;
		this.common = common;
	}

	@Override
	@Transactional(readOnly = true)
	public void create(Filiaal filiaal) {
		filiaal.setId(filiaalDAO.save(filiaal).getId());
		System.out.println(common.getEntityLinks().linkForSingleResource(Filiaal.class, filiaal.getId()));
		mailSender.nieuwFiliaalMail(filiaal, common.getEntityLinks().linkForSingleResource(Filiaal.class, filiaal.getId()).toString());
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public void update(Filiaal filiaal) {
		filiaalDAO.save(filiaal);
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(long id) {
		Filiaal filiaal = filiaalDAO.findOne(id);
		if (filiaal != null) {
			if (!filiaal.getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalDAO.delete(id);
		}
	}

	@Override
	public Iterable<Filiaal> findAll() {
		return filiaalDAO.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalDAO.count();// filiaalDAO.findAantalFilialen();
	}

	@Override
	@PreAuthorize("hasAuthority('manager')")
	public Iterable<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalDAO.findByAdresPostcodeBetweenOrderByNaamAsc(reeks.getVanpostcode(), reeks.getTotpostcode());
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	@Override
	@Scheduled(cron = "0 18 10 15 * ?")
	public void aantalFilialenMail() {
		mailSender.aantalFilialenMail(filiaalDAO.count());
	}
}
