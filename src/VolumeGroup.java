import java.util.ArrayList;

public class VolumeGroup extends Info {
    //Variables
    private String name;
    private String UUIDk;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;

    //Constructors
    public VolumeGroup (String name, String UUID, ArrayList<PhysicalVolume> pv, ArrayList<LogicalVolume> lv) {
        super(name, UUID);
        pvList = pv;
        lvList = lv;
    }
}
