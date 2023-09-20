package simu.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.Asiakas;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class PassportControl extends Palvelupiste {
    private static int counters = 0;
    public PassportControl(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        counters++;
    }

    @Override
    public void aloitaPalvelu() {
        System.out.println("Passport Control: ");
        super.aloitaPalvelu();
    }

    public void passportCheck(){
        
    }
}
