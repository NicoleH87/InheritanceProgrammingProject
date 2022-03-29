import java.util.ArrayList;
import java.util.UUID;

public class Info {
    private String name;
    private String UUIDd;
    private int size;

    public Info (String name, int size)
    {
        this.name = name;
        java.util.UUID u = java.util.UUID.randomUUID();
        UUIDd = u.toString();
        this.size = size;
    }

    public Info (String name)
    {
        this.name = name;
        java.util.UUID u = java.util.UUID.randomUUID();
        UUIDd = u.toString();
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
