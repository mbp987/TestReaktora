package app.model;

public class Questions {
	
	private Integer id_pytania;
	private String jezyk;
	private String tresc;
	private String odp1;
	private String odp2;
	private String odp3;
	private String odp4;
	private String prawidlowa_odp;
	private String czas;
	
	
	public Questions(Integer id_pytania, String jezyk, String tresc, String odp1, String odp2, String odp3, String odp4,
			String prawidlowa_odp, String czas) {
		super();
		this.id_pytania = id_pytania;
		this.jezyk = jezyk;
		this.tresc = tresc;
		this.odp1 = odp1;
		this.odp2 = odp2;
		this.odp3 = odp3;
		this.odp4 = odp4;
		this.prawidlowa_odp = prawidlowa_odp;
		this.czas = czas;
	}
	
	
	public Integer getId_pytania() {
		return id_pytania;
	}
	public void setId_pytania(Integer id_pytania) {
		this.id_pytania = id_pytania;
	}
	public String getJezyk() {
		return jezyk;
	}
	public void setJezyk(String jezyk) {
		this.jezyk = jezyk;
	}
	public String getTresc() {
		return tresc;
	}
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	public String getOdp1() {
		return odp1;
	}
	public void setOdp1(String odp1) {
		this.odp1 = odp1;
	}
	public String getOdp2() {
		return odp2;
	}
	public void setOdp2(String odp2) {
		this.odp2 = odp2;
	}
	public String getOdp3() {
		return odp3;
	}
	public void setOdp3(String odp3) {
		this.odp3 = odp3;
	}
	public String getOdp4() {
		return odp4;
	}
	public void setOdp4(String odp4) {
		this.odp4 = odp4;
	}
	public String getPrawidlowa_odp() {
		return prawidlowa_odp;
	}
	public void setPrawidlowa_odp(String prawidlowa_odp) {
		this.prawidlowa_odp = prawidlowa_odp;
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
		result = prime * result + ((id_pytania == null) ? 0 : id_pytania.hashCode());
		result = prime * result + ((jezyk == null) ? 0 : jezyk.hashCode());
		result = prime * result + ((odp1 == null) ? 0 : odp1.hashCode());
		result = prime * result + ((odp2 == null) ? 0 : odp2.hashCode());
		result = prime * result + ((odp3 == null) ? 0 : odp3.hashCode());
		result = prime * result + ((odp4 == null) ? 0 : odp4.hashCode());
		result = prime * result + ((prawidlowa_odp == null) ? 0 : prawidlowa_odp.hashCode());
		result = prime * result + ((tresc == null) ? 0 : tresc.hashCode());
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
		Questions other = (Questions) obj;
		if (czas == null) {
			if (other.czas != null)
				return false;
		} else if (!czas.equals(other.czas))
			return false;
		if (id_pytania == null) {
			if (other.id_pytania != null)
				return false;
		} else if (!id_pytania.equals(other.id_pytania))
			return false;
		if (jezyk == null) {
			if (other.jezyk != null)
				return false;
		} else if (!jezyk.equals(other.jezyk))
			return false;
		if (odp1 == null) {
			if (other.odp1 != null)
				return false;
		} else if (!odp1.equals(other.odp1))
			return false;
		if (odp2 == null) {
			if (other.odp2 != null)
				return false;
		} else if (!odp2.equals(other.odp2))
			return false;
		if (odp3 == null) {
			if (other.odp3 != null)
				return false;
		} else if (!odp3.equals(other.odp3))
			return false;
		if (odp4 == null) {
			if (other.odp4 != null)
				return false;
		} else if (!odp4.equals(other.odp4))
			return false;
		if (prawidlowa_odp == null) {
			if (other.prawidlowa_odp != null)
				return false;
		} else if (!prawidlowa_odp.equals(other.prawidlowa_odp))
			return false;
		if (tresc == null) {
			if (other.tresc != null)
				return false;
		} else if (!tresc.equals(other.tresc))
			return false;
		return true;
	}
	
	
	
	
	
}
	
	
	
	
	