package il.ac.tau.cs.sw1.ex9.starfleet;

public class TransportShip extends AbstractSpaceship{

    int cargoCapacity;
    int passengerCapacity;

    public TransportShip(String name, int commissionYear, float maximalSpeed,
                         Set<CrewMember> crewMembers, )

    @Override
    public int getAnnualMaintenanceCost() {
        return 0;
    }
}
