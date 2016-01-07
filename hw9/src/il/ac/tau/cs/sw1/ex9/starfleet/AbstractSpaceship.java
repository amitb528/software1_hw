package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

/**
 * Created by Amit on 1/6/2016.
 */
public abstract class AbstractSpaceship implements Spaceship {

    private static final String FORMAT = "%s\n" +
            "\tName=%s\n" +
            "\tCommissionYear=%s\n" +
            "\tMaximalSpeed=%s\n" +
            "\tFirePower=%s\n" +
            "\tCrewMembers=%s\n" +
            "\tAnnualMaintenanceCost=%s";

    String name;
    int commissionYear;
    float maximalSpeed;
    Set<CrewMember> crewMembers;

    public AbstractSpaceship(String name, int commissionYear, float maximalSpeed,
                             Set<CrewMember> crewMembers) {
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalSpeed = maximalSpeed;
        this.crewMembers = crewMembers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCommissionYear() {
        return commissionYear;
    }

    @Override
    public float getMaximalSpeed() {
        return maximalSpeed;
    }

    @Override
    public int getFirePower() {
        return 10;
    }

    @Override
    public Set<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, getClass().getSimpleName(), name, commissionYear, maximalSpeed, getFirePower(),
                crewMembers.size(), getAnnualMaintenanceCost());
    }
}
