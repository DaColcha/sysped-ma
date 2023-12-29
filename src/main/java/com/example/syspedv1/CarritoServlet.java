package com.example.syspedv1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entity.ProductoEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "CarritoServlet", urlPatterns = {"/carrito"})
public class CarritoServlet extends HttpServlet {
    CarritoDeCompras carrito = new CarritoDeCompras();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.readLine();;
        try {
            // Convertir JSON a un mapa de objetos
            Gson gson = new Gson();
            Map<String, Object> productoData = gson.fromJson(json, Map.class);

            // Obtener datos del mapa
            String id = (String) productoData.get("id");
            String imagen = (String) productoData.get("imagen");
            String descripcion = (String) productoData.get("descripcion");
            double precio = (Double) productoData.get("precio");
            int cantidad = ((Double) productoData.get("cantidad")).intValue();
            String detalle = (String) productoData.get("detalle");

            ProductoEntity p = new ProductoEntity();
            p.setPrecio(BigDecimal.valueOf(precio));
            p.setIdProducto(id + "");
            p.setNombreProducto(descripcion);
            p.setImagen(imagen);
            request.setAttribute("carrito", carrito.mostrarCarrito());
            carrito.agregarProducto(p, cantidad, detalle);
            request.setAttribute("lista",carrito.crearFormulario());
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try  {
            Menu menu = new Menu();
            request.setAttribute("menu", menu.mostrarMenu());
            request.setAttribute("carrito", carrito.mostrarCarrito());
            request.setAttribute("lista",carrito.crearFormulario());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.readLine();;
        Gson gson = new Gson();
        Map<String, Object> productoData = gson.fromJson(json, Map.class);
        String id = (String) productoData.get("id");
        if(id.equals("TODO")){
            carrito.eliminarTodo();
        }else {
            carrito.eliminarProducto(id);
        }
        request.setAttribute("lista",carrito.crearFormulario());


    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String datosJson = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Convertir el JSON a una lista de mapas
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> filas = objectMapper.readValue(datosJson, new TypeReference<List<Map<String, String>>>() {});

        // Realizar la lógica de actualización según sea necesario
        for (Map<String, String> fila : filas) {

            String id = fila.get("id");
            int cantidad = Integer.parseInt(fila.get("cantidad"));
            String detalle = fila.get("detalle");

            carrito.actualizar(id,cantidad,detalle);
        }
        request.setAttribute("lista",carrito.crearFormulario());

    }
}
