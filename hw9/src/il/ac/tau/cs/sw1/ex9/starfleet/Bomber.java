package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.List;
import java.util.Set;

public class Bomber extends AttackShip {

    private static final String FORMAT = "%s\n" +
            "\tNumberOfTechnicians=%s";

    int numberOfTechnicians;

    public Bomber(String name, int commissionYear, float maximalSpeed,
                  Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians) {
        super(name, commissionYear, maximalSpeed, crewMembers, weapons);
        this.numberOfTechnicians = numberOfTechnicians;
    }

    public int getNumberOfTechnicians(){
        return numberOfTechnicians;
    }

    @Override
    public int getAnnualMaintenanceCost() {
        return 5000 +
                Math.round((1.0f - (0.1f*numberOfTechnicians)) * getWeaponsAnnualMaintenanceCost());
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), numberOfTechnicians);
    }
}
