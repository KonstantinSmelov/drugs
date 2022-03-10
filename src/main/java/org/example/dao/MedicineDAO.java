package org.example.dao;

import org.example.SortListOfMedicine;
import org.example.entity.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicineDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Medicine> getAllMedicines() {

        Session session = sessionFactory.getCurrentSession();
        List<Medicine> allMedicinesList = session.createQuery("from Medicine", Medicine.class).getResultList();

        return allMedicinesList;
    }

    @Transactional
    public List<Medicine> getAllMedicinesById() {

        Session session = sessionFactory.getCurrentSession();
        List<Medicine> allMedicinesList = session.createQuery("from Medicine", Medicine.class).getResultList();

        return SortListOfMedicine.sortById(allMedicinesList);
    }

    @Transactional
    public List<Medicine> getAllMedicinesByExpData() {

        Session session = sessionFactory.getCurrentSession();
        List<Medicine> allMedicinesList = session.createQuery("from Medicine", Medicine.class).getResultList();


        return SortListOfMedicine.sortByExpData(allMedicinesList);
    }

    @Transactional
    public List<Medicine> getAllMedicinesByName() {

        Session session = sessionFactory.getCurrentSession();
        List<Medicine> allMedicinesList = session.createQuery("from Medicine", Medicine.class).getResultList();

        return SortListOfMedicine.sortByName(allMedicinesList);
    }

    @Transactional
    public List<Medicine> getAllMedicinesByFound(String medicineName) {

        Session session = sessionFactory.getCurrentSession();
        List<Medicine> allMedicinesList = session.createQuery("from Medicine", Medicine.class).getResultList();

        List<Medicine> notFoundList = new ArrayList<>();
        notFoundList.add(new Medicine("не найден", "--", "--", "--", 0));

        if(SortListOfMedicine.sortByFound(allMedicinesList, medicineName).isEmpty() && !medicineName.isEmpty())
            return notFoundList;

        return SortListOfMedicine.sortByFound(allMedicinesList, medicineName);
    }

    @Transactional
    public void saveOrUpdateMedicine(Medicine medicine) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(medicine);
    }

    @Transactional
    public Medicine updateMedicine(int medId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Medicine.class, medId);
    }

    @Transactional
    public void deleteMedicine(int medId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Medicine> query = session.createQuery("delete from Medicine where id = :Id");
        query.setParameter("Id", medId);
        query.executeUpdate();
    }

}
