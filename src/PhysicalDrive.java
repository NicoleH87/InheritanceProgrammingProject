import java.util.ArrayList;
import java.util.UUID;

public class PhysicalDrive extends Info {
    //Variables
    private String name;
    private int size;
    public static ArrayList<String> pD;
    private PhysicalVolume pvAssigned;

    //Constructor
    public PhysicalDrive (String name, int size)
    {
        super(name, size);
    }

    public void checkPhysicalDrive() {
        for (String n : pD)
        {
            if (name.equals(n))
            {
                System.out.println("Physical Drive cannot be created.");
            }
        }
        pD.add(name);
    }

}
