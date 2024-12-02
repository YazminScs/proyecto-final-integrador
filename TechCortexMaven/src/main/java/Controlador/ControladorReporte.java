package Controlador;

import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import Modelo.DetalleCarrito;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorReporte", urlPatterns = {"/ControladorReporte"})
public class ControladorReporte extends HttpServlet {

    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private CarritoDAO carritoDAO = new CarritoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carritoIdParam = request.getParameter("carrito_id");

        if (carritoIdParam == null || carritoIdParam.isEmpty()) {
            response.getWriter().write("Carrito ID no proporcionado.");
            return;
        }

        int carrito_id = Integer.parseInt(carritoIdParam);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tiendita_foley", "root", "");
            
            String sql = "SELECT o.orden_id, p.pago_nom, o.orden_estado, c.carrito_fecha, c.carrito_total, u.usuario_nom "
                    + "FROM carrito c "
                    + "INNER JOIN orden o ON c.carrito_id = o.carrito_id "
                    + "INNER JOIN metodos_pago p ON p.pago_id = o.pago_id "
                    + "JOIN usuario u ON u.usuario_id = c.usuario_id "
                    + "WHERE c.carrito_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, carrito_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int orden_id = rs.getInt("orden_id");
                String pago_nom = rs.getString("pago_nom");
                String orden_estado = rs.getString("orden_estado");
                Date carrito_fecha = rs.getDate("carrito_fecha");
                double carrito_total = rs.getDouble("carrito_total");
                String usuario_nom = rs.getString("usuario_nom");

                // Obtener los detalles del carrito
                String detalle = "SELECT d.detalle_cant, d.detalle_price, p.producto_nom FROM carrito c "
                        + "INNER JOIN detalle_carrito d ON d.carrito_id = c.carrito_id "
                        + "JOIN producto p ON p.producto_id = d.producto_id "
                        + "WHERE c.carrito_id = ?";
                PreparedStatement stmt = conn.prepareStatement(detalle);
                stmt.setInt(1, carrito_id);
                ResultSet rst = stmt.executeQuery();

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=boleta_" + carrito_id + ".pdf");

                Document document = new Document();
                PdfWriter.getInstance(document, response.getOutputStream());

                document.open();
                
                document.add(new Paragraph("Boleta de Orden", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
                document.add(new Paragraph("Cliente: " + usuario_nom));
                document.add(new Paragraph("Número de orden: " + orden_id));
                document.add(new Paragraph("Estado de orden: " + orden_estado));
                document.add(new Paragraph("Método de pago: " + pago_nom));
                document.add(new Paragraph("Fecha: " + carrito_fecha.toString()));
                document.add(new Chunk("\n"));

                PdfPTable table = new PdfPTable(4);
                table.addCell("Producto");
                table.addCell("Cantidad");
                table.addCell("Precio");
                table.addCell("Total");

                BigDecimal totalBoleta = BigDecimal.ZERO;
                while (rst.next()) {
                    String producto = rst.getString("producto_nom");
                    int cantidad = rst.getInt("detalle_cant");
                    BigDecimal precio = new BigDecimal(rst.getDouble("detalle_price")).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal cantidadBD = new BigDecimal(cantidad);
                    BigDecimal totalProducto = precio.multiply(cantidadBD).setScale(2, BigDecimal.ROUND_HALF_UP);

                    table.addCell(producto);
                    table.addCell(String.valueOf(cantidad));
                    table.addCell(precio.toString());
                    table.addCell(totalProducto.toString());

                    totalBoleta = totalBoleta.add(totalProducto);
                }

                document.add(table);
                document.add(new Chunk("\n"));
                document.add(new Paragraph("Total: " + carrito_total));

                document.close();

            } else {
                response.getWriter().write("Carrito no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error en la base de datos.");
        } catch (DocumentException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("Error al generar el PDF.");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
