import java.util.ArrayList;
import java.util.UUID;

public class PhysicalDrive extends Info {
    //Variables
    private String name;
    private int size;
    private PhysicalVolume pvAssigned;

    //Constructor
    public PhysicalDrive (String name, int size)
    {
        super(name, size);
    }

    public void setPhysicalVolume(PhysicalVolume physicalVolume){
        pvAssigned = physicalVolume;
    }

    public PhysicalVolume getPhysicalVolume(){
        return pvAssigned;
    }
}
