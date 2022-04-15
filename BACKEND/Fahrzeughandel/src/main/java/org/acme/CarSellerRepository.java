package org.acme;

import org.acme.Model.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CarSellerRepository {

    @Inject
    protected EntityManager em;

    public List<Car> getAvailableCars(){
        return em.createQuery("SELECT c FROM Car c WHERE c.isBought = false and c.isReserved = false").getResultList();
    }

    public List<Car> getReservedCarsOfUser(int userId){
        var userQuery = em.createQuery("SELECT u FROM Benutzer u WHERE u.UserID = :userId", Benutzer.class);
        userQuery.setParameter("userId", userId);

        try{
            Benutzer benutzer = userQuery.getSingleResult();

            return benutzer.getReservedCars();

        }catch(NoResultException ex){
            System.out.println(ex.getMessage());
        }

        List<Car> noCars = new ArrayList<>();
        return noCars;
    }

    public int checkUser(String un, String pw){
        List<Benutzer> benutzers = em.createQuery("SELECT u FROm Benutzer u").getResultList();
        for (int i = 0; i < benutzers.size(); i++) {
            if(benutzers.get(i).getUsername() == un && benutzers.get(i).getPassword() == pw)
                return benutzers.get(i).getUserID();
        }
        return -1;
    }


    @Transactional
    public int createUser(String un, String pw){
        Benutzer benutzer = new Benutzer(un, pw);
        em.merge(benutzer);
        return benutzer.getUserID();
    }

    @Transactional
    public void reserveUnreserveCar(int userId, int carId){
        var carQuery = em.createQuery("SELECT c FROM Car c WHERE c.CarID = :carId", Car.class);
        carQuery.setParameter("carId", carId);

        var userQuery = em.createQuery("SELECT u FROM Benutzer u WHERE u.UserID = :userId", Benutzer.class);
        userQuery.setParameter("userId", userId);

        try{
            Car car = carQuery.getSingleResult();
            Benutzer benutzer = userQuery.getSingleResult();

            car.setReserved(!car.isReserved());

            if(benutzer.getReservedCars().contains(car)){
                List<Car> reservedCars = benutzer.getReservedCars();
                reservedCars.remove(car);
                benutzer.setReservedCars(reservedCars);
            }
            else{
                List<Car> reserveList = benutzer.getReservedCars();
                reserveList.add(car);
                benutzer.setReservedCars(reserveList);
            }

            em.merge(car);
            em.merge(benutzer);
        }catch (NoResultException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Transactional
    public void buyCar(int userId, int carId) {
        var carQuery = em.createQuery("SELECT c FROM Car c WHERE c.CarID = :carId", Car.class);
        carQuery.setParameter("carId", carId);

        var userQuery = em.createQuery("SELECT u FROM Benutzer u WHERE u.UserID = :userId", Benutzer.class);
        userQuery.setParameter("userId", userId);

        try {
            Car car = carQuery.getSingleResult();
            Benutzer benutzer = userQuery.getSingleResult();

            car.setBought(true);

            List<Car> list = benutzer.getBoughtCars();
            list.add(car);
            benutzer.setBoughtCars(list);

            em.merge(car);
            em.merge(benutzer);

        }catch(NoResultException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    public void addCar(Car car){
        em.merge(car);
    }

}




































