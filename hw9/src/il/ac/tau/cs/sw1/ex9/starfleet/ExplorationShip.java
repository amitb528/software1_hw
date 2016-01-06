package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.Set;

public class ExplorationShip extends AbstractSpaceship{

    int numberOfResearchLabs;

    public ExplorationShip(String name, int commissionYear, float maximalSpeed,
                           Set<CrewMember> crewMembers, int numberOfResearchLabs){
        super(name, commissionYear, maximalSpeed, crewMembers);
        this.numberOfResearchLabs = numberOfResearchLabs;
    }

    @Override
    public int getAnnualMaintenanceCost() {
        return 4000 + 2500 * numberOfResearchLabs;
    }
}
