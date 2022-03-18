import java.util.ArrayList;
import java.util.UUID;

public class Info {
    private String name;
    private String UUIDd;
    private int size;

    public Info (String name, String UUID, int size)
    {
        name = this.name;
        java.util.UUID u = java.util.UUID.randomUUID();
        UUIDd = u.toString();
        size = this.size;
    }

    public Info (String name, String UUID)
    {
        name = this.name;
        java.util.UUID u = java.util.UUID.randomUUID();
        UUIDd = u.toString();
    }

    public Info (String name, int size)
    {
        name = this.name;
        size = this.size;
    }

    public String getName() {
        return name;
    }

    public String getUUIDd() {
        return UUIDd;
    }

    public int getSize() {
        return size;
    }
}
