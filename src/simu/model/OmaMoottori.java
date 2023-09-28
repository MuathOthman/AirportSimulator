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

		palvelupisteet = new Palvelupiste[6];

		palvelupisteet[0]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);
		palvelupisteet[1]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);
		palvelupisteet[2]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);
		palvelupisteet[3]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);
		palvelupisteet[4]=new CheckIN(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP1);

		palvelupisteet[5]=new SecurityCheck(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.DEP2);


		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}


	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

		Asiakas a;
		boolean jonocheck;
		int j;
		switch ((TapahtumanTyyppi) t.getTyyppi()) {

			case ARR1:
				//palvelupisteet[0].lisaaJonoon(new Asiakas()); //asiakas menee check-iniin
				//saapumisprosessi.generoiSeuraava();
				jonocheck = true;
				j=0;
				while (jonocheck) {

					for (int i = 0; i <5; i++) {
						if (palvelupisteet[i].GetJonoSize() == j) {
							palvelupisteet[i].lisaaJonoon(new Asiakas());
							System.out.println("Asiakas lisätty check iniin nro" + i);
							jonocheck = false;
							break;
						}
					}
					j++;
				}
				saapumisprosessi.generoiSeuraava();
				break;
			case DEP1:
				a = (Asiakas) palvelupisteet[0].otaJonosta(); //asiakas siirtyy check-inistä turvatarkastukseen
				palvelupisteet[5].lisaaJonoon(a);
				break;
			case DEP2:
				System.out.println("poisto");
				a = (Asiakas) palvelupisteet[5].otaJonosta(); //asiakas poistetaan järjestelmästä
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();

		}
	}

		@Override
		protected void yritaCTapahtumat () {
			for (Palvelupiste p : palvelupisteet) {
				if (!p.onVarattu() && p.onJonossa()) {
					p.aloitaPalvelu();
				}
			}
		}

		@Override
		protected void tulokset () {
			System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
			System.out.println("Järjestelmä ehti palvella " + Asiakas.getCount() + " asiakasta.");

		}


	}
