package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

/**
 * Created by Amit on 1/6/2016.
 */
public abstract class AbstractSpaceship implements Spaceship{

    String name;
    int commissionYear;
    float maximalSpeed;
    Set<CrewMember> crewMembers;

    public AbstractSpaceship(String name, int commissionYear, float maximalSpeed,
                             Set<CrewMember> crewMembers){
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
}
