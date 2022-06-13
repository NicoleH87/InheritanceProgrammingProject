import java.util.ArrayList;
import java.util.Scanner;

public class VolumeGroup extends Info {
    //Variables
    private ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
    private ArrayList<LogicalVolume> lvList = new ArrayList<LogicalVolume>();

    //Constructors
    public VolumeGroup (String name, PhysicalVolume pv) {
        super(name);
        pvList.add(pv);
    }

    public void addPV (PhysicalVolume pv) {
        pvList.add(pv);
    }

    public void addLV(LogicalVolume lv)
    {
        lvList.add(lv);
    }

    public boolean canCreateLV(int newSize)
    {
        int totalSpace = getVGSize();
        if (totalSpace + newSize < this.getSize())
        {
            return true;
        }
        return false;
    }

    public ArrayList<PhysicalVolume> getPVList()
    {
        return pvList;
    }

    public int getVGSize()
    {
        int totalSize = 0;
        for (PhysicalVolume physVol : pvList)
        {
            totalSize += physVol.getPD().getSize();
        }
        return totalSize;
    }

    public int freeSpace()
    {
        int lvSize = 0;
        for (LogicalVolume logVol: lvList) {
            lvSize += logVol.getSize();
        }
        return getVGSize() - lvSize;
    }
}
