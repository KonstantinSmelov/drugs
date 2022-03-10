package org.example;

import org.example.entity.Medicine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MedComparatorClass implements Comparator<Medicine> {

    @Override
    public int compare(Medicine o1, Medicine o2) {
        return o1.getIntExpiration().compareTo(o2.getIntExpiration());
    }
}

public class SortListOfMedicine {

    public static Integer myRevers(String str) {
        StringBuilder sb = new StringBuilder(str);
        StringBuilder sbToOut = new StringBuilder("");

        sb.deleteCharAt(2);
        sbToOut.append(sb.charAt(2));
        sbToOut.append(sb.charAt(3));
        sbToOut.append(sb.charAt(4));
        sbToOut.append(sb.charAt(5));
        sbToOut.append(sb.charAt(0));
        sbToOut.append(sb.charAt(1));

        System.out.println(Integer.parseInt(sbToOut.toString()));
        return Integer.parseInt(sbToOut.toString());
    }

    public static List<Medicine> sortByFound(List<Medicine> allMedicineList, String medicineName) {

        List<Medicine> foundMedicine = new ArrayList<>();

        for (Medicine medicine : allMedicineList)
            if (medicine.getMedicine().equals(medicineName)) {
                foundMedicine.add(medicine);
            }

        return foundMedicine;
    }

    public static List<Medicine> sortByName(List<Medicine> allMedicineList) {

        Collections.sort(allMedicineList);

        return allMedicineList;
    }

    public static List<Medicine> sortByExpData(List<Medicine> allMedicineList) {

        for(Medicine med : allMedicineList) {

            med.setIntExpiration(myRevers(med.getExpiration()));

        }

        Collections.sort(allMedicineList, new MedComparatorClass());

//        List<Medicine> leftList = new ArrayList<>();
//        List<Medicine> rightList = new ArrayList<>();
//        List<Medicine> toOut = new ArrayList<>();
//        int pivot = allMedicineList.size() / 2;
//        List <Medicine> pivotList = new ArrayList<>();
//
//        if (allMedicineList.size() < 2)
//            return allMedicineList;
//        else {
//            for (int x = 0; x < allMedicineList.size(); x++) {
//                if (myRevers(allMedicineList.get(x).getExpiration()) < myRevers(allMedicineList.get(pivot).getExpiration())) {          // наполняем левую часть
//
//                    leftList.add(allMedicineList.get(x));
//
//                } else if (myRevers(allMedicineList.get(x).getExpiration()) > myRevers(allMedicineList.get(pivot).getExpiration()))    // наполняем правую часть
//                {
//
//                    rightList.add(allMedicineList.get(x));
//
//                }
//
//            }
//        }
//
//        toOut.addAll(sortByExpData(leftList));
//        toOut.addAll(sortByExpData(pivotList));
//        toOut.addAll(sortByExpData(rightList));

        return allMedicineList;
    }

    public static List<Medicine> sortById(List<Medicine> allMedicineList) {

        List<Medicine> leftList = new ArrayList<>();
        List<Medicine> rightList = new ArrayList<>();
        List<Medicine> toOut = new ArrayList<>();
        int pivot = allMedicineList.size() / 2;

        if (allMedicineList.size() < 2)
            return allMedicineList;
        else {
            for (int x = 0; x < allMedicineList.size(); x++) {
                if (allMedicineList.get(x).getId() < allMedicineList.get(pivot).getId()) {          // наполняем левую часть
                    leftList.add(allMedicineList.get(x));
                } else if (allMedicineList.get(x).getId() > allMedicineList.get(pivot).getId())    // наполняем правую часть
                {
                    rightList.add(allMedicineList.get(x));
                }
            }
        }

        toOut.addAll(sortById(leftList));
        toOut.add(allMedicineList.get(pivot));
        toOut.addAll(sortById(rightList));

        return toOut;
    }
}
