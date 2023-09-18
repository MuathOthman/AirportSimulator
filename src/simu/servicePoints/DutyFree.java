package simu.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class DutyFree extends Palvelupiste {
    private static int cashers = 0;
    public DutyFree(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        cashers++;
    }

    @Override
    public void aloitaPalvelu() {
        System.out.println("Duty Free: ");
        super.aloitaPalvelu();
    }
}
