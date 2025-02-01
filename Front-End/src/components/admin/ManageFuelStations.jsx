import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ManageFuelStations = () => {
  const [stations, setStations] = useState([]); // Always an array
  const navigate = useNavigate();

  // Fetch stations data from backend
  useEffect(() => {
    const fetchStations = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/FuelStation/getStations"
        );
        console.log("API Response:", response.data);

        // Assuming the API returns an object with a 'stations' property:
        if (response.data && Array.isArray(response.data.stations)) {
          setStations(response.data.stations);
        } else if (Array.isArray(response.data)) {
          // If the API returns an array directly:
          setStations(response.data);
        } else {
          setStations([]);
        }
      } catch (error) {
        console.error("Error fetching stations:", error);
        setStations([]);
      }
    };

    fetchStations();
  }, []);

  // Navigate to the update page for the station
  const handleUpdate = (stationId) => {
    navigate(`/update-fuel-station/${stationId}`);
  };

  // Delete station from the backend and update state
  const handleDelete = async (stationId) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/v1/FuelStation/delete/${stationId}`
      );
      // Update local state by filtering out the deleted station
      setStations((prevStations) =>
        prevStations.filter((station) => station.id !== stationId)
      );
    } catch (error) {
      console.error("Error deleting station:", error);
    }
  };

  return (
    <div className="min-h-screen p-8 bg-green-50">
      <div className="max-w-4xl p-6 mx-auto bg-white rounded-lg shadow-lg">
        <h1 className="mb-4 text-2xl font-bold text-center text-green-700">
          Manage Fuel Stations
        </h1>
        <table className="w-full text-black border border-collapse border-gray-300">
          <thead>
            <tr className="bg-green-200">
              <th className="px-4 py-2 border">Station Name</th>
              <th className="px-4 py-2 border">Location</th>
              <th className="px-4 py-2 border">Contact</th>
              <th className="px-4 py-2 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            {Array.isArray(stations) && stations.length > 0 ? (
              stations.map((station) => (
                <tr key={station.id} className="odd:bg-white even:bg-green-50">
                  <td className="px-4 py-2 border">{station.stationName}</td>
                  <td className="px-4 py-2 border">
                    {station.stationLocation}
                  </td>
                  <td className="px-4 py-2 border">{station.stationContact}</td>
                  <td className="px-4 py-2 border">
                    <button
                      onClick={() => handleUpdate(station.id)}
                      className="px-2 py-1 mr-2 text-white bg-blue-500 rounded hover:bg-blue-600"
                    >
                      Update
                    </button>
                    <button
                      onClick={() => handleDelete(station.id)}
                      className="px-2 py-1 text-white bg-red-500 rounded hover:bg-red-600"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="px-4 py-2 text-center">
                  No stations found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ManageFuelStations;
