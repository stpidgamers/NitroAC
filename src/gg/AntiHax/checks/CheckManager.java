package gg.AntiHax.checks;


import java.util.ArrayList;
import java.util.List;

public class CheckManager {

    private List<Check> checks = new ArrayList<>();

    public CheckManager() {
        //rchecks.add(new Speed());
        //checks.add(new Pattern("Pattern", CheckType.COMBAT, true, true, 10));
        //checks.add(new SpeedA("SpeedA", CheckType.MOVEMENT, true, true, 20));

    }

    public List<Check> getChecks() {
        return checks;
    }
}