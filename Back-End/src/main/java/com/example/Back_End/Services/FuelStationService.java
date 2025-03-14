package com.example.Back_End.Services;

import com.example.Back_End.DTO.FuelStationDTO;
import com.example.Back_End.DTO.FuelStationRetrieveDTO;
import com.example.Back_End.DTO.FuelStationRegisterDTO;
import com.example.Back_End.Exceptions.FuelStationException;

import java.util.List;

public interface FuelStationService {

    String addFuelStation(FuelStationRegisterDTO fuelStationRegisterDTO);
    FuelStationDTO getFuelStation(String stationName) throws Exception;
    FuelStationDTO updateFuelStation(int stationID, FuelStationDTO fuelStationDTO) throws FuelStationException;
    FuelStationDTO updateFuelStation(FuelStationDTO fuelStationDTO) throws FuelStationException;
    String deleteFuelStation(String stationName) throws FuelStationException;
    String deleteFuelStation(int stationID) throws FuelStationException;
    List<FuelStationRetrieveDTO> getAllFuelStations();
    FuelStationDTO getFuelStationByID(int stationID) throws FuelStationException;
    int getStaionIDByUserID(int userID) throws FuelStationException;
}
