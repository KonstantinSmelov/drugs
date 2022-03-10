package org.example.controller;

import org.example.SortListOfMedicine;
import org.example.dao.MedicineDAO;
import org.example.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MedicineController {

    @Autowired
    private MedicineDAO medicineDAO;
    String currentSort = "ByName";

    @RequestMapping("/")
    public String showAllMedicines(Model model) {

        List<Medicine> allMedicineList = medicineDAO.getAllMedicines();

        Medicine medicine = new Medicine();

        model.addAttribute("allMedicine", allMedicineList);
        model.addAttribute("medicineName", medicine.getMedicine());

        return "/all-drugs-list";
    }

    @RequestMapping("/sortById")
    public String showAllMedicinesById(Model model) {

        List<Medicine> allMedicineList = medicineDAO.getAllMedicinesById();
        model.addAttribute("allMedicine", allMedicineList);

        return "/all-drugs-list";
    }

    @RequestMapping("/sortByName")
    public String showAllMedicinesByName(Model model) {

        List<Medicine> allMedicineList = medicineDAO.getAllMedicinesByName();
        model.addAttribute("allMedicine", allMedicineList);

        return "/all-drugs-list";
    }

    @RequestMapping("/sortByExpDate")
    public String showAllMedicinesByExpData(Model model) {

        List<Medicine> allMedicineList = medicineDAO.getAllMedicinesByExpData();
        model.addAttribute("allMedicine", allMedicineList);

        return "/all-drugs-list";
    }


    @RequestMapping("/addNewMedicine")
    public String addNewMedicine(Model model) {

        Medicine medicine = new Medicine();
        model.addAttribute("drug", medicine);

        return "/drug-info";
    }

    @RequestMapping("/saveMedicine")
    public String saveMedicine(@ModelAttribute("drug") Medicine medicine) {

        medicineDAO.saveOrUpdateMedicine(medicine);

        if (currentSort == "ByName")
            return "redirect:/sortByName";

        if (currentSort == "ById")
            return "redirect:/sortById";

        return "redirect:/";
    }

    @RequestMapping("/updateMedicine")
    public String updateMedicine(@ModelAttribute("medId") int medId, Model model) {

        Medicine medicine = medicineDAO.updateMedicine(medId);
        model.addAttribute("drug", medicine);

        return "/drug-info";
    }

    @RequestMapping("/deleteMedicine")
    public String deleteMedicine(@ModelAttribute("medId") int medId) {

        medicineDAO.deleteMedicine(medId);

        if (currentSort == "ByName")
            return "redirect:/sortByName";

        if (currentSort == "ById")
            return "redirect:/sortById";

        return "redirect:/";
    }

    @RequestMapping("/findMedicine")
    public String findMedicine(@RequestParam("medicineName") String medicineName, Model model) {

        List<Medicine> allMedicineList = medicineDAO.getAllMedicinesByName();

        List<Medicine> foundMedicineList = medicineDAO.getAllMedicinesByFound(medicineName);

        if(!foundMedicineList.isEmpty())
            model.addAttribute("allMedicine", foundMedicineList);
        else
            model.addAttribute("allMedicine", allMedicineList);

        return "/all-drugs-list";
    }
}
