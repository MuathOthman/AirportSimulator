package simu.Terminals;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.TapahtumanTyyppi;
import simu.servicePoints.CheckIN;

public class International extends CheckIN {
    public International(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }

    @Override
    public void aloitaPalvelu() {
        System.out.println("Check-IN: ");
        super.aloitaPalvelu();
    }
}
