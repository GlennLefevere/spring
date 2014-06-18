package be.vdab.commons;

import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

@Component
public class CommonImpl implements Common{
	private EntityLinks entityLinks;
	
	public void setEntityLinks(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	public EntityLinks getEntityLinks() {
		return entityLinks;
	}

}
