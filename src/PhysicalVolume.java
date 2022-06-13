import java.util.Scanner;

public class PhysicalVolume extends Info {
    private PhysicalDrive pd;
    private VolumeGroup vg;
    String vgName;

    public PhysicalVolume (String name, PhysicalDrive pd)
    {
        super(name);
        this.pd = pd;
    }

    public PhysicalDrive getPD()
    {
        return pd;
    }

    public VolumeGroup getVG() {
        return vg;
    }

    public String getVGName() {
        if (vg == null) {
            return "";
        }
        else {
            return vg.getName();
        }
    }

    public void setPD(PhysicalDrive pd)
    {
        this.pd = pd;
    }

    public void setVG(VolumeGroup vg) {
        this.vg = vg;
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
