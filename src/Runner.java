import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("exit"))
        {
            input = input.substring(5);
            int index = input.indexOf(" ");
            String command = input.substring(0, index);
            ArrayList<PhysicalDrive> pDList = new ArrayList<PhysicalDrive>();
            ArrayList<PhysicalDrive> pDSet = new ArrayList<PhysicalDrive>();
            ArrayList<PhysicalVolume> pvList = new ArrayList<PhysicalVolume>();
            ArrayList<PhysicalVolume> pvSet = new ArrayList<PhysicalVolume>();
            ArrayList<VolumeGroup> vgList = new ArrayList<VolumeGroup>();

            if (command.equals("install-drive"))
            {
                input = input.substring(index + 1);
                index = input.indexOf(" ");
                String name = input.substring(0, index);
                String size = input.substring(index + 1);
                if (size.indexOf("G") != -1)
                {
                    int tempIn = size.indexOf("G");
                    size = input.substring(index + 1, tempIn);
                }
                int pDSize = Integer.parseInt(size);
                PhysicalDrive PD = new PhysicalDrive (name, pDSize);
                pDList.add(PD);
                System.out.println("Drive " + name + "installed");
            }
            if (command.equals("list-drives"))
            {
                for (PhysicalDrive physDrive : pDList)
                {
                    System.out.println(physDrive.getName() + " [" + physDrive.getSize() + "G]");
                }
            }
            if (command.equals("pvcreate"))
            {
                input = input.substring(index + 1);
                index = input.indexOf(" ");
                String name = input.substring(0, index);
                String driveName = input.substring(index + 1);
                boolean canCreate = true;
                for (int i = 0; i < pDList.size(); i++)
                {
                    if (pDList.get(i).getName() == driveName)
                    {
                        for (int k = 0; k < pDSet.size(); k++)
                        {
                            if (pDSet.get(k).getName() == driveName)
                            {
                                System.out.println("Drive is already associated with a created PV.");
                                canCreate = false;
                            }
                        }
                    }
                    else
                    {
                        canCreate = false;
                    }
                }
                if (canCreate == false)
                {
                    System.out.println("Drive does not exist.");
                }
                else
                {
                    PhysicalVolume pv = new PhysicalVolume(name);
                    for (int i = 0; i < pDList.size(); i++)
                    {
                        if (pDList.get(i).getName() == driveName)
                        {
                            pv.setPd(pDList.get(i));
                            pDSet.add(pDList.get(i));
                        }
                    }
                    pvList.add(pv);
                    System.out.println(pv.getName() + "created");
                }
            }
            if (command.equals("vgcreate")) {
                input = input.substring(index + 1);
                index = input.indexOf(" ");
                String name = input.substring(0, index);
                String pvName = input.substring(index + 1);
                for (int i = 0; i < vgList.size(); i++) {
                    if (name.equals(vgList.get(i).getName())) {
                        System.out.println("Name already exists.");
                    }
                }
                PhysicalVolume inputPV;
                for (int i = 0; i < pvList.size(); i++) {
                    if (pvList.get(i).getName().equals(pvName)) {
                        boolean present = false;
                        for (int p = 0; p < pvSet.size(); p++) {
                            if (pvSet.get(p).getName().equals(pvName)) {
                                System.out.println("There is an error"); //PV in another VG.
                                present = true;
                            }
                        }
                        if (present == false) {
                            inputPV = pvList.get(i);
                            VolumeGroup vg = new VolumeGroup(name, inputPV);
                            vgList.add(vg);
                            pvSet.add(inputPV);
                        }
                    } else {
                        System.out.println("There is an error.");
                    }
                }
            }
            if (command.equals("vgextend")) {
                input = input.substring(index + 1);
                index = input.indexOf(" ");
                String name = input.substring(0, index);
                String pvName = input.substring(index + 1);
                VolumeGroup vg;
                boolean vgExists = false;
                for (int i = 0; i < vgList.size(); i++) {
                    if (name.equals(vgList.get(i).getName())) {
                        vgExists = true;
                        vg = vgList.get(i);
                        PhysicalVolume pv;
                        boolean pvExists = false;
                        for (int t = 0; t < pvList.size(); t++) {
                            if (pvName.equals(pvList.get(t).getName())) {
                                pvExists = true;
                                pv = pvList.get(i);
                                if (vgExists == true && pvExists == true) {
                                    vg.addPV(pv);
                                } else {
                                    System.out.println("There is an error");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
