package simu.model;

import simu.Terminals.Domestic;
import simu.Terminals.International;
import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.servicePoints.CheckIN;
import simu.servicePoints.DutyFree;
import simu.servicePoints.PassportControl;
import simu.servicePoints.SecurityCheck;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;

	public OmaMoottori(){

		palvelupisteet = new Palvelupiste[5];

		palvelupisteet[0]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1); //check-in
		palvelupisteet[1]=new SecurityCheck(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.DEP2); //turvatarkastus
		palvelupisteet[2]=new PassportControl(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP3); //passikontrolli
		palvelupisteet[3]=new DutyFree(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP4); //tax-free
		palvelupisteet[4]=new International(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP5); //kansainvälinen terminaali
		//palvelupisteet[5]=new Domestic(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.DEP6); //sisämaan terminaali

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

			case ARR1: palvelupisteet[0].lisaaJonoon(new Asiakas()); //asiakas menee check-iniin
				       saapumisprosessi.generoiSeuraava();
				break;
			case DEP1: a = (Asiakas)palvelupisteet[0].otaJonosta(); //asiakas siirtyy check-inistä turvatarkastukseen
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case DEP2: a = (Asiakas)palvelupisteet[1].otaJonosta(); //asiakas siirtyy turvatarkastuksesta  sisamaan terminaaliin tai passikontrolliin
						if (a.international) {
							palvelupisteet[2].lisaaJonoon(a);
						} else {
							palvelupisteet[5].lisaaJonoon(a);
						}

				break;
			case DEP3: a = (Asiakas)palvelupisteet[2].otaJonosta(); //asiakas siirtyy passitarkastuksesta tax-freehin
				       palvelupisteet[3].lisaaJonoon(a);
					   break;
			case DEP4:
				       a = (Asiakas)palvelupisteet[3].otaJonosta(); //asiakas siirtyy tax-freesta kansainväliseen terminaaliin
						palvelupisteet[4].lisaaJonoon(a);
						break;
			case DEP5:
						System.out.println("case5");
						a = (Asiakas)palvelupisteet[4].otaJonosta(); //asiakas poistetaan järjestelmästä
						a.setPoistumisaika(Kello.getInstance().getAika());
						a.raportti();
			/*case DEP6:
						System.out.println("case6");
						a = (Asiakas)palvelupisteet[5].otaJonosta(); //asiakas poistetaan järjestelmästä
						a.setPoistumisaika(Kello.getInstance().getAika());
						a.raportti(); */
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
		System.out.println("Järjestelmä ehti palvella " + Asiakas.getCount() + " asiakasta.");

	}

	
}
