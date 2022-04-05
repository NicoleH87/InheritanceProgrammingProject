import java.util.ArrayList;
import java.util.Scanner;

public class VolumeGroup extends Info {
    //Variables
    private String name;
    private String UUIDk;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;

    //Constructors
    public VolumeGroup (String name, PhysicalVolume pv) {
        super(name);
        pvList.add(pv);
    }

    public void addPV (PhysicalVolume pv) {
        pvList.add(pv);
    }

    public void createLV(String name, int size)
    {
        if (canCreateLV(size))
        {
            LogicalVolume logicVol = new LogicalVolume(size, name);
            lvList.add(logicVol);
        }
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

    public void createPV(String name)
    {
        PhysicalVolume physVol = new PhysicalVolume (name);
        pvList.add(physVol);
    }

    public int getVGSize()
    {
        int totalSize = 0;
        for (LogicalVolume logicVol : lvList)
        {
            totalSize += logicVol.getSize();
        }
        return totalSize;
    }

    public int freeSpace()
    {
        return this.getSize() - getVGSize();
    }
}
