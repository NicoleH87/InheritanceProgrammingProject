import java.util.UUID;

public class PhysicalDrive {
    //Variables
    private String name;
    private int size;

    //Constructor
    public PhysicalDrive (String name, int size)
    {
        name = this.name;
        size = this.size;
    }

    //Methods
    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }
}
