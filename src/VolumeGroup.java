import java.util.ArrayList;
import java.util.UUID;

public class VolumeGroup extends PhysicalVolume {
    //Variables
    private String name;
    private String UUIDk;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;

    //Constructors
    public VolumeGroup (PhysicalDrive input, String name, String vgName) {
        super(input, name);
        name = vgName;
        java.util.UUID u = java.util.UUID.randomUUID();
        UUIDk = u.toString();

    }
}
