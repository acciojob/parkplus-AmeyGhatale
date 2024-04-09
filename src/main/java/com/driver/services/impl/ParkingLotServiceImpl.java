package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot(name, address);
        parkingLot = parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        Spot spot = new Spot(parkingLot, pricePerHour);
        if(numberOfWheels==2)
            spot.setSpotType(SpotType.TWO_WHEELER);
        else if (numberOfWheels==4)
                spot.setSpotType(SpotType.FOUR_WHEELER);
        else
            spot.setSpotType(SpotType.OTHERS);

        spotRepository1.save(spot);

        return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot = spotRepository1.findById(spotId).get();
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
//        parkingLot.getSpotList().get(spotId-1).setPricePerHour(pricePerHour);
        spot.setPricePerHour(pricePerHour);
        spotRepository1.save(spot);
        parkingLotRepository1.save(parkingLot);
        return spot;
//        parkingLot.se
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
