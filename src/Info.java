import java.util.UUID;

public class Info {
    private String name;
    private String UUIDd;

    public Info (String name, String UUID)
    {
        name = this.name;
        UUID u = UUID.randomUUID();
        UUIDd = u.toString();
    }
}
