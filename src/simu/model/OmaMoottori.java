package simu.model;

import servicePoints.CheckIn;
import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;
	private CheckIn[] checkIns;

	public OmaMoottori(){

		palvelupisteet = new Palvelupiste[3];
		checkIns = new CheckIn[1];

		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.DEP2);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP3);
		checkIns[0]=new CheckIn(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECK1);
		checkIns[0].setName("Muath");

		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}


	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch ((TapahtumanTyyppi)t.getTyyppi()){

			case ARR1: palvelupisteet[0].lisaaJonoon(new Asiakas());
				       saapumisprosessi.generoiSeuraava();
				break;
			case DEP1: a = (Asiakas)palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case DEP2: a = (Asiakas)palvelupisteet[1].otaJonosta();
				   	   palvelupisteet[2].lisaaJonoon(a);
				break;
			case CHECK1: a = (Asiakas)palvelupisteet[2].otaJonosta();
				   	   checkIns[0].lisaaJonoon(a);
						System.out.println(checkIns[0].getName());
						System.out.println(a.getId());
				break;
			case DEP3: a = (Asiakas)checkIns[0].otaJonosta();
					   System.out.println(checkIns[0].getName());
					   //System.out.println(a.getId());
					   //a.setPoistumisaika(Kello.getInstance().getAika());
					   //a.raportti();
		}
	}

	@Override
	protected void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
