package simu.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class CheckIN extends Palvelupiste {
    private static int counters = 0;
    private static int customers = 0;
    public CheckIN(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        counters++;
    }

    public int CustomersServed() {
        return customers;
    }

    @Override
    public void aloitaPalvelu() {
        System.out.println("Check-IN: ");
        super.aloitaPalvelu();
    }
}
