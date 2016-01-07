package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter{

    static int numberOfStealthCruisers = 0;

    public StealthCruiser(String name, int commissionYear, float maximalSpeed,
                          Set<CrewMember> crewMembers, List<Weapon> weapons) {
        super(name, commissionYear, maximalSpeed, crewMembers, weapons);
        numberOfStealthCruisers++;
    }

    public StealthCruiser(String name, int commissionYear, float maximalSpeed,
                          Set<CrewMember> crewMembers){
        this(name, commissionYear, maximalSpeed, crewMembers, null);

        List<Weapon> weapons = new ArrayList<Weapon>();
        weapons.add(new Weapon("Laser Cannons", 10, 100));
        this.weapons = weapons;
    }

    @Override
    public int getAnnualMaintenanceCost() {
        return super.getAnnualMaintenanceCost() +
                numberOfStealthCruisers * 100;
    }
}
