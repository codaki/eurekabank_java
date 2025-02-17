// /controller/mapaSucursalesController.js
export async function fetchAndAddMarkers(map) {
  try {
    const response = await fetch("http://localhost:8002/api/sucursal");
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const sucursales = await response.json();

    sucursales.forEach((sucursal) => {
      if (sucursal.latitude && sucursal.longitude) {
        const position = { lat: sucursal.latitude, lng: sucursal.longitude };
        const marker = new google.maps.Marker({
          position: position,
          map: map,
          title: `${sucursal.nombre} - ${sucursal.ciudad}`,
        });

        // Optional: Add info window
        const infoWindow = new google.maps.InfoWindow({
          content: `<strong>${sucursal.nombre}</strong><br/>${sucursal.direccion}<br/>Ciudad: ${sucursal.ciudad}`,
        });

        marker.addListener("click", () => {
          infoWindow.open(map, marker);
        });
      }
    });
  } catch (error) {
    console.error("Error fetching sucursales:", error);
  }
}

// Expose the function globally
window.fetchAndAddMarkers = fetchAndAddMarkers;
