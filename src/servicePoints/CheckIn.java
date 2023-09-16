package servicePoints;
import java.util.LinkedList;


import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.model.Asiakas;
import simu.model.Palvelupiste;
import simu.model.TapahtumanTyyppi;

public class CheckIn extends Palvelupiste {
    private boolean varattu = false; // Initialize as needed
    private Tapahtumalista tapahtumalista; // Initialize as needed
    private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; // Initialize as needed
    private LinkedList<Asiakas> jono = new LinkedList<>(); // Initialize as needed

    public CheckIn(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }

    @Override
    public void aloitaPalvelu() {
        Trace.out(Trace.Level.INFO, "Asiakas check-in " + jono.peek().getId());
        varattu = true;
        double palveluaika = generator.sample();
        tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika()+palveluaika));
    }
}
