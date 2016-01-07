package il.ac.tau.cs.sw1.ex9.starfleet;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Set;

/**
 * Created by Amit on 1/7/2016.
 */
public abstract class AttackShip extends AbstractSpaceship{

    private static final String FORMAT = "%s\n" +
            "\tWeaponArray=%s";

    List<Weapon> weapons;

    public AttackShip(String name, int commissionYear, float maximalSpeed,
                      Set<CrewMember> crewMembers, List<Weapon> weapons){
        super(name, commissionYear, maximalSpeed, crewMembers);
        this.weapons = weapons;
    }

    @Override
    public int getFirePower() {
        int extraFirePower = 0;
        for(Weapon w : weapons){
            extraFirePower += w.getFirePower();
        }
        return super.getFirePower() + extraFirePower;
    }

    protected int getWeaponsAnnualMaintenanceCost(){
        int cost = 0;
        for(Weapon w : weapons){
            cost += w.getAnnualMaintenanceCost();
        }
        return cost;
    }

    public List<Weapon> getWeapon(){
        return weapons;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), weapons);
    }
}
