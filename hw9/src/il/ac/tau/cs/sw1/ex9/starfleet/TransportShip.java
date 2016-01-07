package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbstractSpaceship{

    private static final String FORMAT = "%s\n" +
            "\tCargoCapacity=%s\n" +
            "\tPassengerCapacity=%s";

    int cargoCapacity;
    int passengerCapacity;

    public TransportShip(String name, int commissionYear, float maximalSpeed,
                         Set<CrewMember> crewMembers, int cargoCapacity,
                         int passengerCapacity){
        super(name, commissionYear, maximalSpeed, crewMembers);
        this.cargoCapacity = cargoCapacity;
        this.passengerCapacity = passengerCapacity;
    }

    public int getCargoCapacity(){
        return cargoCapacity;
    }

    public int getPassenerCapacity(){
        return passengerCapacity;
    }

    @Override
    public int getAnnualMaintenanceCost() {
        return 3000 + 5*cargoCapacity + 3*passengerCapacity;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), cargoCapacity, passengerCapacity);
    }
}
