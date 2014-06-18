package be.vdab.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;

import be.vdab.commons.Common;

@Configuration
@ComponentScan(basePackageClasses = CreateRestControllerBeans.class)
public class CreateRestControllerBeans {
	@Autowired
	public void fillCommonWithEntityLinks(EntityLinks entityLinks, Common common) {
		common.setEntityLinks(entityLinks);
	}
}
