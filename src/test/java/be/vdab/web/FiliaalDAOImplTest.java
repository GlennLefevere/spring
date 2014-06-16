package be.vdab.web;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.vdab.dao.CreateTestDAOBeans;
import be.vdab.dao.FiliaalDAO;
import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDAOBeans.class, CreateTestDataSourceBean.class })
public class FiliaalDAOImplTest {
	@Autowired
	private FiliaalDAO filiaalDAO;

	@Test
	public void create() {
		Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE, new Date(), new Adres("Straat", "HuisNr", 1000, "Gemeente"));
		filiaalDAO.save(filiaal);
		Assert.assertNotEquals(0, filiaal.getId());
	}
	@Test
	public void delete(){
		Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE, new Date(), new Adres("Straat", "HuisNr", 1000, "Gemeente"));
		filiaalDAO.save(filiaal);
		filiaalDAO.delete(0L);
	}
}
