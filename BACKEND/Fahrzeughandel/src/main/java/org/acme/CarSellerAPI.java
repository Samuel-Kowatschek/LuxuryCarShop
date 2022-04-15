package org.acme;


import org.acme.Model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("CarSellerDB")
public class CarSellerAPI {

    @Inject
    private CarSellerRepository db;

    @GET
    @Path("availableCars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getAvailabkeCars() {
        return db.getAvailableCars();
    }

    @GET
    @Path("reservedCars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getReservedCarsOfUser(@PathParam("id") int id){
        return db.getReservedCarsOfUser(id);
    }

    @Path("reserve/{userId}/{carId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateReservationOfUser(@PathParam("userId") int userId, @PathParam("carId") int carId) {
        db.reserveUnreserveCar(userId, carId);
        return "Change applied";
    }

    @Path("buy/{userId}/{carId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String buyCar(@PathParam("userId") int userId, @PathParam("carId") int carId){
        db.buyCar(userId, carId);
        return "Car bought";
    }

    @Path("addCar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCar(Car car){
        db.addCar(car);
        //System.out.println(car.getCarID() + " : " + car.getCarType() + " : " + car.getCarModel() + " : " + car.getPrice());
        return "Car added";
    }
    
}
