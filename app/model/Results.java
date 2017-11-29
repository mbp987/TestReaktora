package app.model;

public class Results {

	private Integer id_wynik;
	private String login;
	private String jezyk;
	private Integer liczba_pytan;
	private Integer wynik;
	private String czas;

	private Users user = new Users();

	public Results(String login, String imie, String nazwisko, String grupa, Integer id_wynik, String jezyk, Integer liczba_pytan, Integer wynik, String czas) {
		super();
		this.id_wynik = id_wynik;
		this.login = login;
		this.jezyk = jezyk;
		this.liczba_pytan = liczba_pytan;
		this.wynik = wynik;
		this.czas = czas;
		user.setImie(imie);
		user.setNazwisko(nazwisko);
		user.setGrupa(grupa);
		user.setLogin(login);
	}
	
	public Results(String grupa, String jezyk, Integer liczba_pytan, Integer wynik) {
		user.setGrupa(grupa);
		this.jezyk = jezyk;
		this.liczba_pytan = liczba_pytan;
		this.wynik = wynik;
	}

	public Integer getId_wynik() {
		return id_wynik;
	}

	public void setId_wynik(Integer id_wynik) {
		this.id_wynik = id_wynik;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getJezyk() {
		return jezyk;
	}

	public void setJezyk(String jezyk) {
		this.jezyk = jezyk;
	}

	public Integer getLiczba_pytan() {
		return liczba_pytan;
	}

	public void setLiczba_pytan(Integer liczba_pytan) {
		this.liczba_pytan = liczba_pytan;
	}

	public Integer getWynik() {
		return wynik;
	}

	public void setWynik(Integer wynik) {
		this.wynik = wynik;
	}

	public String getCzas() {
		return czas;
	}

	public void setCzas(String czas) {
		this.czas = czas;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((czas == null) ? 0 : czas.hashCode());
		result = prime * result + ((id_wynik == null) ? 0 : id_wynik.hashCode());
		result = prime * result + ((jezyk == null) ? 0 : jezyk.hashCode());
		result = prime * result + ((liczba_pytan == null) ? 0 : liczba_pytan.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((wynik == null) ? 0 : wynik.hashCode());
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
		Results other = (Results) obj;
		if (czas == null) {
			if (other.czas != null)
				return false;
		} else if (!czas.equals(other.czas))
			return false;
		if (id_wynik == null) {
			if (other.id_wynik != null)
				return false;
		} else if (!id_wynik.equals(other.id_wynik))
			return false;
		if (jezyk == null) {
			if (other.jezyk != null)
				return false;
		} else if (!jezyk.equals(other.jezyk))
			return false;
		if (liczba_pytan == null) {
			if (other.liczba_pytan != null)
				return false;
		} else if (!liczba_pytan.equals(other.liczba_pytan))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (wynik == null) {
			if (other.wynik != null)
				return false;
		} else if (!wynik.equals(other.wynik))
			return false;
		return true;
	}

}
