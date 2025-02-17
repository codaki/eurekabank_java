// /controller/mapaSucursalesController.js
export async function fetchAndAddMarkers(map, userPosition) {
  try {
    const response = await fetch("http://localhost:8002/api/sucursal");
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const sucursales = await response.json();

    const distanceService = new google.maps.DistanceMatrixService();

    // Prepare destinations for distance calculation
    const destinations = sucursales
      .filter((s) => s.latitude && s.longitude)
      .map((s) => ({ lat: s.latitude, lng: s.longitude }));

    // Calculate distances from user's position to each branch
    distanceService.getDistanceMatrix(
      {
        origins: [userPosition],
        destinations: destinations,
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
      },
      (response, status) => {
        if (status !== "OK") {
          console.error("Error en DistanceMatrixService:", status);
          return;
        }

        // Add markers with distance info
        response.rows[0].elements.forEach((element, index) => {
          const sucursal = sucursales[index];
          const position = { lat: sucursal.latitude, lng: sucursal.longitude };

          const marker = new google.maps.Marker({
            position: position,
            map: map,
            title: `${sucursal.nombre} - ${sucursal.ciudad}`,
          });

          const distance = element.distance?.text || "Desconocida";
          const duration = element.duration?.text || "Desconocido";

          const infoWindow = new google.maps.InfoWindow({
            content: `
              <strong>${sucursal.nombre}</strong><br/>
              Dirección: ${sucursal.direccion}<br/>
              Ciudad: ${sucursal.ciudad}<br/>
              Distancia: ${distance}<br/>
              Tiempo estimado: ${duration}
            `,
          });

          marker.addListener("click", () => {
            infoWindow.open(map, marker);
          });
        });
      }
    );
  } catch (error) {
    console.error("Error fetching sucursales:", error);
  }
}

// Expose the function globally
window.fetchAndAddMarkers = fetchAndAddMarkers;
