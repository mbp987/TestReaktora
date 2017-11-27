package app.model;

public class Users {

	private String login;
	private String haslo;
	private String imie;
	private String nazwisko;
	private String grupa;
	private String rola;
	private String czas;

	public Users() {

	}

	public Users(String login, String haslo, String imie, String nazwisko, String grupa, String rola, String czas) {
		super();
		this.login = login;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.grupa = grupa;
		this.rola = rola;
		this.czas = czas;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getGrupa() {
		return grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public String getCzas() {
		return czas;
	}

	public void setCzas(String czas) {
		this.czas = czas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((czas == null) ? 0 : czas.hashCode());
		result = prime * result + ((grupa == null) ? 0 : grupa.hashCode());
		result = prime * result + ((haslo == null) ? 0 : haslo.hashCode());
		result = prime * result + ((imie == null) ? 0 : imie.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nazwisko == null) ? 0 : nazwisko.hashCode());
		result = prime * result + ((rola == null) ? 0 : rola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (czas == null) {
			if (other.czas != null)
				return false;
		} else if (!czas.equals(other.czas))
			return false;
		if (grupa == null) {
			if (other.grupa != null)
				return false;
		} else if (!grupa.equals(other.grupa))
			return false;
		if (haslo == null) {
			if (other.haslo != null)
				return false;
		} else if (!haslo.equals(other.haslo))
			return false;
		if (imie == null) {
			if (other.imie != null)
				return false;
		} else if (!imie.equals(other.imie))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nazwisko == null) {
			if (other.nazwisko != null)
				return false;
		} else if (!nazwisko.equals(other.nazwisko))
			return false;
		if (rola == null) {
			if (other.rola != null)
				return false;
		} else if (!rola.equals(other.rola))
			return false;
		return true;
	}

}
