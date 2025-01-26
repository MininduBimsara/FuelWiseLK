package com.example.Back_End.Services;

import com.example.Back_End.DTO.FuelStationDTO;
import com.example.Back_End.Exceptions.FuelStationException;

import java.util.List;

public interface FuelStationService {

    String addFuelStation(FuelStationDTO fuelStationDTO);
    FuelStationDTO getFuelStation(String stationName) throws Exception;
    FuelStationDTO updateFuelStation(FuelStationDTO fuelStationDTO) throws FuelStationException;
    String deleteFuelStation(String stationName) throws FuelStationException;
    List<FuelStationDTO> getAllFuelStations();
}
