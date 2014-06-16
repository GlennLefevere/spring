package be.vdab.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class VoorkeurenImpl implements Voorkeur, Serializable{
	private static final long serialVersionUID = 1L;
	private String foto;
	@Override
	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String getFoto() {
		return foto;
	}

}
