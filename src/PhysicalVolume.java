public class PhysicalVolume extends Info {
    private String UUIDd;
    private String name;

    public PhysicalVolume (PhysicalDrive pv, String name, String UUID)
    {
        super(name, UUID);
    }


}
