package servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class CheckIn extends Palvelupiste {
    public CheckIn(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }
}
