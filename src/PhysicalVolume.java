import java.util.Scanner;

public class PhysicalVolume extends Info {
    private PhysicalDrive pd;

    public PhysicalVolume (String name)
    {
        super(name);
        if (pd == null)
        {
            createPD();
        }
    }

    public void setPd(PhysicalDrive pd)
    {
        this.pd = pd;
    }

    public PhysicalDrive getPd()
    {
        return pd;
    }

    public void createPD()
    {
        System.out.println("Enter the name of your physical drive: ");
        Scanner s = new Scanner (System.in);
        String n = s.nextLine();
        System.out.println("Enter size: ");
        int size = s.nextInt();
        pd = new PhysicalDrive(n, size);
    }


}
