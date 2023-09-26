package simu.servicePoints;

import simu.model.*;
import simu.framework.*;
import java.util.ArrayList;
import java.util.LinkedList;

//WIP
public class ServicePointManager {

    private ArrayList<Palvelupiste> pisteet = new ArrayList<>();
    private LinkedList<Asiakas> poistuvat = new LinkedList<>();


    public ServicePointManager() {

    }

    public void lisaaJonoon(Asiakas a) {

    }

    public Asiakas siirraAsiakas() {
        return poistuvat.remove();

    }


}
