public class LogicalVolume extends Info {
    private int size;
    private String UUID;
    private String name;
    private VolumeGroup vg;

    public LogicalVolume (int size, String UUID, String name, VolumeGroup vg)
    {
        super(name, UUID, size);
        vg = this.vg;
    }

}
