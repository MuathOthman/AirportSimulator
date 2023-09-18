package simu.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class SecurityCheck extends Palvelupiste {
    private static int counters = 0;
    public SecurityCheck(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        counters++;
    }

    @Override
    public void aloitaPalvelu() {
        System.out.println("Security Control: ");
        super.aloitaPalvelu();
    }
}
