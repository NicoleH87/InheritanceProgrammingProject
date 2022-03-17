import java.util.UUID;

public class PhysicalVolume extends Info {
    private String UUIDd;
    private String name;
    private PhysicalDrive pdrive;

    public PhysicalVolume (PhysicalDrive input, String name, String UUID)
    {
        name = this.name();
        pdrive = input;
        String id = UUID;

    }


}
