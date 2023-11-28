package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDTO;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDTO> findAllCars(){
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("Select c from Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            List<CarDTO> carsDTO = new ArrayList<>();
            cars.forEach((car)->{
                CarDTO carDTO = new CarDTO(car.getId(), car.getLicensePlate(), car.getParkingSpot(),car.getOwner().getUsername());
                carsDTO.add(carDTO);
            });
            return carsDTO;
        }
        catch (Exception ex){
            throw  new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, long userId){
        LOG.info("create car");

        //create car
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        //get owner
        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        //save car in db
        entityManager.persist(car);
    }

    public CarDTO findById(Long carId) {
        Car car = entityManager.find(Car.class,carId);
        return new CarDTO(car.getId(),car.getLicensePlate(),car.getParkingSpot(),car.getOwner().getUsername());
    }
}
