public class LogicalVolume extends Info {
    private VolumeGroup vg;

    public LogicalVolume (String name, int size, VolumeGroup vg)
    {
        super(name, size);
        this.vg = vg;
    }

    public VolumeGroup getVG() {
        return vg;
    }

}
