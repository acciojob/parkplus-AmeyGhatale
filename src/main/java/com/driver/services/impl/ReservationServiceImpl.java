package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
            User user = userRepository3.findById(userId).get();
            ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
            Spot spot = new Spot();
            for (Spot spot1 : parkingLot.getSpotList())
            {
                spot = spot1;
                if (spot.getParkingLot().getId()==parkingLotId){
                    if(numberOfWheels==2)
                        spot.setSpotType(SpotType.TWO_WHEELER);
                    else if (numberOfWheels==4)
                        spot.setSpotType(SpotType.FOUR_WHEELER);
                    else
                        spot.setSpotType(SpotType.OTHERS);
                    spotRepository3.save(spot);
                    break;
                }
            }

            Reservation reservation = new Reservation(user, spot, timeInHours);
            reservation = reservationRepository3.save(reservation);
            return reservation;

    }
}
