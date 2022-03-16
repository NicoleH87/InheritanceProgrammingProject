import java.util.UUID;

public class PV {
    private String UUIDd;
    private String name;
    private PhysicalDrive pdrive;

    public PV (PhysicalDrive input)
    {
        name = input.getName();
        pdrive = input;
        UUID u = UUID.randomUUID();
        UUIDd = u.toString();
    }


}
