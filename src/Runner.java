import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Info info = new Info("Info");
        ArrayList<PhysicalDrive> pdList = new ArrayList<PhysicalDrive>();
        ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
        ArrayList<VolumeGroup> vgList = new ArrayList<VolumeGroup>();
        ArrayList<LogicalVolume> lvList = new ArrayList<LogicalVolume>();

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the LVM system! Enter your commands: ");
        System.out.println("");
        System.out.print("cmd# ");
        String input = s.nextLine();

        while (!input.equals("exit")) {
            int index = input.indexOf(" ");
            String command;
            if (index == -1) {
                command = input;
            }
            else {
                command = input.substring(0, index);
                input = input.substring(index + 1);
            }
            if (command.equals("install-drive")) {
                index = input.indexOf(" ");
                if (index != -1) {
                    String driveName = input.substring(0, index);
                    boolean pdExist = false;
                    for (PhysicalDrive pd: pdList) {
                        if (pd.getName().equals(driveName)) {
                            pdExist = true;
                            System.out.println("Drive " + driveName + " already exists.");
                        }
                    }
                    if (pdExist == false) {
                        int size = Integer.parseInt(input.substring(index + 1, input.length() - 1));
                        PhysicalDrive pd = new PhysicalDrive(driveName, size);
                        pdList.add(pd);
                        System.out.println("Drive " + driveName + " installed");
                    }
                }
                else {
                    System.out.println("Invalid command format.");
                }
            }
            else if (command.equals("list-drives")) {
                for (PhysicalDrive pd: pdList) {
                    System.out.println(pd.getName() + " [" + pd.getSize() + "]");
                }
            }
            else if (command.equals("pvcreate")) {
                index = input.indexOf(" ");
                if (index != -1) {
                    String pvName = input.substring(0, index);
                    String pvDriveName = input.substring(index + 1);
                    PhysicalDrive drive = new PhysicalDrive(" ", 0);
                    boolean pvExist = false;
                    boolean driveHasPV = false;
                    boolean driveExists = false;
                    for (PhysicalDrive pd: pdList) {
                        if (pd.getName().equals(pvDriveName)) {
                            driveExists = true;
                            drive = pd;
                            if (pd.getPhysicalVolume() != null) {
                                driveHasPV = true;
                            }
                        }
                    }
                    for (PhysicalVolume pv: pvList) {
                        if (pv.getName().equals(pvName)) {
                            pvExist = true;
                        }
                    }
                    if (pvExist == false) {
                        if (driveExists == true) {
                            if (driveHasPV == false) {
                                PhysicalVolume pv = new PhysicalVolume(pvName, drive);
                                drive.setPhysicalVolume(pv);
                                pvList.add(pv);
                                System.out.println(pvName + " created");
                            }
                            else {
                                System.out.println("Drive " + pvDriveName + " is already associated with a physical volume.");
                            }
                        }
                        else
                        {
                            System.out.println("Drive " + pvDriveName + " does not exist.");
                        }
                    }
                    else {
                        System.out.println("Physical Volume " + pvName + " already exists.");
                    }
                }
                else {
                    System.out.println("Invalid command format.");
                }
            }
            else if (command.equals("pvlist")) {
                ArrayList<PhysicalVolume> currPVList = pvList;
                for (int i = 0; i < currPVList.size(); i++) {
                    String tempVG = currPVList.get(i).getVGName();
                    int time = 0;
                    for (int k = i + 1; k < currPVList.size(); k++) {
                        if (currPVList.get(k).getVGName().equals(tempVG)) {
                                currPVList.add(i + 1 + time, currPVList.remove(k));
                                time++;
                        }
                    }
                    i = i + time - 1;
                }
                for (int i = 0; i < currPVList.size(); i++) {
                    System.out.print(currPVList.get(i).getName() + ":[" + currPVList.get(i).getPD().getSize() + "G] [");
                    if (currPVList.get(i).getVG() != null) {
                        System.out.print(currPVList.get(i).getVG().getName() + "] [");
                    }
                    System.out.println(currPVList.get(i).getUUIDd() + "]");
                }
            }
            else if (command.equals("vgcreate")) {
                index = input.indexOf(" ");
                if (index != -1) {
                    String vgName = input.substring(0, index);
                    String pvVGName = input.substring(index + 1);
                    PhysicalVolume volume = new PhysicalVolume(" ", new PhysicalDrive(" ", 0));
                    boolean vgExist = false;
                    boolean pvExist = false;
                    boolean vgHasPV = false;
                    for (PhysicalVolume pv: pvList) {
                        if (pv.getName().equals(pvVGName)) {
                            pvExist = true;
                            volume = pv;
                            if (pv.getVG() != null) {
                                vgHasPV = true;
                            }
                        }
                    }
                    for (VolumeGroup vg: vgList) {
                        if (vg.getName().equals(vgName)) {
                            vgExist = true;
                        }
                    }
                    if (vgExist == false) {
                        if (pvExist == true) {
                            if (vgHasPV == false) {
                                VolumeGroup vg = new VolumeGroup(vgName, volume);
                                volume.setVG(vg);
                                vgList.add(vg);
                                System.out.println(vgName + " created");
                            }
                            else {
                                System.out.println("Physical Volume " + pvVGName + " is already associated with a volume group.");
                            }
                        }
                        else
                        {
                            System.out.println("Physical Volume " + pvVGName + " does not exist.");
                        }
                    }
                    else {
                        System.out.println("Volume Group " + vgName + " already exists.");
                    }
                }
                else {
                    System.out.println("Invalid command format.");
                }
            }
            else if (command.equals("vgextend")) {
                index = input.indexOf(" ");
                if (index != -1) {
                    String vgName = input.substring(0, index);
                    String pvName = input.substring(index + 1);
                    PhysicalVolume volume = new PhysicalVolume(" ", new PhysicalDrive(" ", 0));
                    boolean vgExist = false;
                    boolean pvExist = false;
                    boolean vgHasPV = false;
                    for (PhysicalVolume pv: pvList) {
                        if (pv.getName().equals(pvName)) {
                            pvExist = true;
                            volume = pv;
                            if (pv.getVG() != null) {
                                vgHasPV = true;
                            }
                        }
                    }
                    VolumeGroup group = new VolumeGroup(" ", volume);
                    for (VolumeGroup vg: vgList) {
                        if (vg.getName().equals(vgName)) {
                            vgExist = true;
                            group = vg;
                        }
                    }
                    if (vgExist == true) {
                        if (pvExist == true) {
                            if (vgHasPV == false) {
                                group.addPV(volume);
                                volume.setVG(group);
                                System.out.println(vgName + " extended to " + pvName);
                            }
                            else {
                                System.out.println("Physical Volume " + pvName + " is already associated with a volume group.");
                            }
                        }
                        else
                        {
                            System.out.println("Physical Volume " + pvName + " does not exist.");
                        }
                    }
                    else {
                        System.out.println("Volume Group " + vgName + " does not exist.");
                    }
                }
                else {
                    System.out.println("Invalid command format.");
                }
            }
            else if (command.equals("vglist")) {
                for (VolumeGroup vg: vgList) {
                    System.out.print(vg.getName() + ": total:[" + vg.getVGSize() + "G] available:[" + vg.freeSpace() + "G] ");
                    ArrayList<PhysicalVolume> currPV = vg.getPVList();
                    System.out.print("[" + currPV.get(0).getName());
                    for (int i = 1; i < currPV.size() ; i++) {
                        System.out.print("," + currPV.get(i).getName());
                    }
                    System.out.println("] [" + vg.getUUIDd() + "]");
                }
            }
            else if (command.equals("lvcreate")) {
                index = input.indexOf(" ");
                if (index != -1) {
                    String lvName = input.substring(0, index);
                    input = input.substring(index + 1);
                    index = input.indexOf(" ");
                    if (index != -1) {
                        String lvSize = input.substring(0, index);
                        if (lvSize.indexOf("G") != -1) {
                            lvSize = lvSize.substring(0, lvSize.length());
                        }
                        int size = Integer.parseInt(lvSize);
                        String lvVGName = input.substring(index + 1);
                        PhysicalVolume volume = new PhysicalVolume(" ", new PhysicalDrive(" ", 0));
                        VolumeGroup group = new VolumeGroup(" ", volume);
                        boolean vgExist = false;
                        boolean lvExist = false;
                        boolean hasSpace = false;
                        for (VolumeGroup vg: vgList) {
                            if (vg.getName().equals(lvVGName)) {
                                vgExist = true;
                                group = vg;
                                if (group.freeSpace() >= size) {
                                    hasSpace = true;
                                }
                            }
                        }
                        for (LogicalVolume lv: lvList) {
                            if (lv.getName().equals(lvName)) {
                                lvExist = true;
                            }
                        }
                        if (lvExist == false) {
                            if (vgExist == true) {
                                if (hasSpace == true) {
                                    LogicalVolume lv = new LogicalVolume(lvName, size, group);
                                    group.addLV(lv);
                                    lvList.add(lv);
                                    System.out.println(lvName + " created");
                                }
                                else {
                                    System.out.println("Volume Group " + lvVGName + " does not have enough space.");
                                }
                            }
                            else
                            {
                                System.out.println("Volume Group " + lvVGName + " does not exist.");
                            }
                        }
                        else {
                            System.out.println("Logical Volume " + lvName + " already exists.");
                        }
                    }
                    else {
                        System.out.println("Invalid command format.");
                    }
                }
                else {
                    System.out.println("Invalid command format.");
                }
            }
            else if (command.equals("lvlist")) {
                for (LogicalVolume lv: lvList) {
                    System.out.println(lv.getName() + ": [" + lv.getSize() + "] [" + lv.getVG().getName() + "] [" + lv.getUUIDd() + "]");
                }
            }
            else {
                System.out.println("Invalid Command.");
            }
            System.out.println();
            System.out.print("cmd# ");
            input = s.nextLine();
        }
    }
}
