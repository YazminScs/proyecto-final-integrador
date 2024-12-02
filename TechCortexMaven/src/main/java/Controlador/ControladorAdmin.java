package Controlador;

import DAO.CategoriaDAO;
import DAO.MarcaDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name = "ControladorAdmin", urlPatterns = {"/ControladorAdmin"})
public class ControladorAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            request.setAttribute("mensaje", "Error: La acción no fue especificada.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            return;
        }
        if (accion.equals("paginaProductos")) {
            cargarMarcas(request);
            cargarCategorias(request);
            cargarProductos(request);
            request.getRequestDispatcher("/Vista/admin-productos.jsp").forward(request, response);
        } else if (accion.equals("paginaCategorias")) {
            cargarCategorias(request);
            request.getRequestDispatcher("/Vista/admin-categorias.jsp").forward(request, response);
        } else if (accion.equals("paginaMarcas")) {
            cargarMarcas(request);
            request.getRequestDispatcher("/Vista/admin-marcas.jsp").forward(request, response);
        } else if (accion.equals("paginaUsuarios")) {
            cargarUsuarios(request);
            request.getRequestDispatcher("/Vista/admin-usuarios.jsp").forward(request, response);
        } else if (accion.equals("eliminarUsuario")) {
            eliminarUsuario(request, response);
        } else if (accion.equals("generarExcelUsuarios")) {
            generarExcelUsuarios(request, response);
        } else if (accion.equals("eliminarProducto")) {
            eliminarProducto(request, response);
        } else if (accion.equals("generarExcelProducto")) {
            generarExcelProductos(request, response);
        } else if (accion.equals("eliminarCategoria")) {
            eliminarCategoria(request, response);
        } else if (accion.equals("generarExcelCategoria")) {
            generarExcelCategorias(request, response);
        } else if (accion.equals("eliminarMarca")) {
            eliminarMarca(request, response);
        }else if (accion.equals("generarExcelMarcas")) {
            generarExcelMarcas(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            request.setAttribute("mensaje", "Error: La acción no fue especificada.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            return;
        }
        if (accion.equals("editarUsuario")) {
            editarAdminUsuario(request, response);
        } else if (accion.equals("registrarUsuario")) {
            registrarUsuario(request, response);
        } else if (accion.equals("editarProducto")) {
            editarProducto(request, response);
        } else if (accion.equals("registrarProducto")) {
            registrarProducto(request, response);
        } else if (accion.equals("editarCategoria")) {
            editarCategoria(request, response);
        } else if (accion.equals("registrarCategoria")) {
            registrarCategoria(request, response);
        } else if (accion.equals("registrarMarca")) {
            agregarMarca(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarProductos(HttpServletRequest request) {
        List<Producto> listaProductos = new ProductoDAO().listarProductos();
        request.setAttribute("listaProductos", listaProductos);
    }

    private void cargarCategorias(HttpServletRequest request) {
        List<Categoria> listaCategorias = new CategoriaDAO().listarCategoria();
        request.setAttribute("listaCategorias", listaCategorias);
    }

    private void cargarMarcas(HttpServletRequest request) {
        List<Marca> listaMarcas = new MarcaDAO().listarMarcas();
        request.setAttribute("listaMarcas", listaMarcas);
    }

    private void cargarUsuarios(HttpServletRequest request) {
        List<Usuario> listaUsuarios = new UsuarioDAO().obtenerUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
    }

    private void editarAdminUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        try {
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setAddress(address);
            usuario.setPhone(phone);

            boolean actualizado = new UsuarioDAO().actualizarUsuarioPorId(usuario);

            if (actualizado) {
                request.setAttribute("mensaje", "Usuario actualizado exitosamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el usuario. Inténtalo nuevamente.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("mensaje", "Ocurrió un error inesperado: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaUsuarios");
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer los datos enviados desde el formulario
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String rol = request.getParameter("rol");

        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setAddress(address);
            usuario.setPhone(phone);
            usuario.setRol(rol);

            boolean registrado = new UsuarioDAO().registrarUsuario(username, email, password, address, phone, rol);

            if (registrado) {
                request.getSession().setAttribute("mensaje", "Usuario registrado exitosamente.");
            } else {
                request.getSession().setAttribute("mensaje", "No se pudo registrar el usuario. Inténtalo nuevamente.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("mensaje", "Ocurrió un error inesperado: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaUsuarios");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {

            boolean eliminado = new UsuarioDAO().eliminarUsuarioPorId(id);

            if (eliminado) {
                request.getSession().setAttribute("mensaje", "Usuario eliminado exitosamente.");
            } else {
                request.getSession().setAttribute("mensaje", "No se pudo eliminar el usuario. Inténtalo nuevamente.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("mensaje", "Ocurrió un error inesperado: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaUsuarios");
    }

    private void generarExcelUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener la lista de usuarios desde tu DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();

        // Configurar la respuesta HTTP para el archivo Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.xlsx");

        // Crear el archivo Excel usando Apache POI
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Usuarios");

            // Crear una fuente para el título
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para la fila del título
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());  // Morado
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear la fila para el título de la tabla
            Row titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("Lista de Usuarios");
            titleRow.getCell(0).setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6)); // Combinar celdas para el título

            // Crear una fuente para los encabezados
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para los encabezados de las columnas
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex()); // Morado
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            // Estilo para las celdas de datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setAlignment(HorizontalAlignment.CENTER);

            String[] columnas = {"ID", "Nombre", "Email", "Contraseña", "Rol", "Dirección", "Teléfono"};

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(1);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // Llenar los datos de los usuarios
            int rowIndex = 2; // Los datos empiezan después de la fila de encabezado
            for (Usuario usuario : usuarios) {
                Row row = sheet.createRow(rowIndex++);
                createCell(row, 0, String.valueOf(usuario.getId()), dataStyle);
                createCell(row, 1, usuario.getUsername(), dataStyle);
                createCell(row, 2, usuario.getEmail(), dataStyle);
                createCell(row, 3, usuario.getPassword(), dataStyle);  // Opcional: considera no incluir la contraseña
                createCell(row, 4, usuario.getRol(), dataStyle);
                createCell(row, 5, usuario.getAddress(), dataStyle);
                createCell(row, 6, String.valueOf(usuario.getPhone()), dataStyle);
            }

            // Ajustar automáticamente las columnas
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo Excel a la respuesta HTTP
            workbook.write(response.getOutputStream());
        }
    }

    private void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombreProducto = request.getParameter("nombreProducto");
        String descripcionProducto = request.getParameter("descripcionProducto");
        double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
        int stockProducto = Integer.parseInt(request.getParameter("stockProducto"));
        int categoriaId = Integer.parseInt(request.getParameter("categoriaProducto"));
        int marcaId = Integer.parseInt(request.getParameter("marcaProducto"));
        String imagenProducto = request.getParameter("imagenProducto");

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre(nombreProducto);
        producto.setDescripcion(descripcionProducto);
        producto.setPrecio(precioProducto);
        producto.setStock(stockProducto);
        producto.setUrl_imagen(imagenProducto);

        // Establecer la categoría y marca usando los IDs recibidos
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaId);
        producto.setCategorias(categoria);

        Marca marca = new Marca();
        marca.setIdMarca(marcaId);
        producto.setMarcas(marca);

        ProductoDAO productoDAO = new ProductoDAO();
        boolean actualizado = productoDAO.actualizarProductos(producto);

        if (actualizado) {
            // Redirigir a la lista de productos o mostrar un mensaje de éxito
            request.getSession().setAttribute("mensaje", "Usuario editado exitosamente.");
        } else {
            // Manejar error
            request.setAttribute("mensaje", "Error al actualizar el producto.");

        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaProductos");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean eliminado = new ProductoDAO().eliminarProducto(id);

        if (eliminado) {
            request.getSession().setAttribute("mensaje", "Usuario eliminado exitosamente.");
        } else {
            request.getSession().setAttribute("mensaje", "No se pudo eliminar el usuario. Inténtalo nuevamente.");
        }
        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaProductos");
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            String nombre = request.getParameter("nombreProducto");
            String descripcion = request.getParameter("descripcionProducto");
            Double precio = Double.parseDouble(request.getParameter("precioProducto"));
            int stock = Integer.parseInt(request.getParameter("stockProducto"));
            String urlImagen = request.getParameter("imagenProducto");
            int idCategoria = Integer.parseInt(request.getParameter("categoriaProducto"));
            int idMarca = Integer.parseInt(request.getParameter("marcaProducto"));

            // Crear objeto Producto
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setUrl_imagen(urlImagen);

            // Crear las categorías y marcas basadas en los IDs
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            producto.setCategorias(categoria);

            Marca marca = new Marca();
            marca.setIdMarca(idMarca);
            producto.setMarcas(marca);

            // Llamar al método de registro en el DAO
            ProductoDAO productoDAO = new ProductoDAO();
            boolean registrado = productoDAO.registrarProducto(producto);

            if (registrado) {
                request.setAttribute("mensaje", "Producto registrado exitosamente.");
            } else {
                request.setAttribute("mensaje", "Error al registrar el producto.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error al registrar el producto.");
        }

        // Redirigir a la página correspondiente
        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaProductos");
    }

    private void generarExcelProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener la lista de productos desde el DAO
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductos();

        // Configurar la respuesta HTTP para el archivo Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");

        // Crear el archivo Excel usando Apache POI
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Productos");

            // Crear una fuente para el título
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para la fila del título
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());  // Lavanda
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear la fila para el título de la tabla
            Row titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("Lista de Productos");
            titleRow.getCell(0).setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); // Combinar celdas para el título

            // Crear una fuente para los encabezados
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para los encabezados de las columnas
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex()); // Lavanda
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            // Estilo para las celdas de datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setAlignment(HorizontalAlignment.LEFT);

            String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock", "URL Imagen", "Categoría", "Marca"};

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(1);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // Llenar los datos de los productos
            int rowIndex = 2; // Los datos empiezan después de la fila de encabezado
            for (Producto producto : productos) {
                Row row = sheet.createRow(rowIndex++);
                createCell(row, 0, String.valueOf(producto.getIdProducto()), dataStyle);
                createCell(row, 1, producto.getNombre(), dataStyle);
                createCell(row, 2, producto.getDescripcion(), dataStyle);
                createCell(row, 3, String.valueOf(producto.getPrecio()), dataStyle);
                createCell(row, 4, String.valueOf(producto.getStock()), dataStyle);
                createCell(row, 5, producto.getUrl_imagen(), dataStyle);
                createCell(row, 6, producto.getCategorias().getNombre(), dataStyle);
                createCell(row, 7, producto.getMarcas().getNombre(), dataStyle);
            }

            // Ajustar automáticamente las columnas
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo Excel a la respuesta HTTP
            workbook.write(response.getOutputStream());
        }
    }

    private void registrarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nombre = request.getParameter("nombreCategoria");

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        boolean registrado = new CategoriaDAO().agregarCategoria(categoria);

        if (registrado) {
            request.setAttribute("mensaje", "Categoria registrado exitosamente.");
        } else {
            request.setAttribute("mensaje", "Error al registrar la categoria.");
        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaCategorias");
    }

    private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int CategoriaId = Integer.parseInt(request.getParameter("CategoriaId"));
        String nombreCategoria = request.getParameter("nombre");

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(CategoriaId);
        categoria.setNombre(nombreCategoria);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean actualizado = categoriaDAO.actualizarCategoria(categoria);
        if (actualizado) {
            // Redirigir a la lista de productos o mostrar un mensaje de éxito
            request.getSession().setAttribute("mensaje", "Usuario editado exitosamente.");
        } else {
            // Manejar error
            request.setAttribute("mensaje", "Error al actualizar el producto.");

        }
        // Redirigir a la página correspondiente
        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaCategorias");
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean eliminado = new CategoriaDAO().eliminarCategoria(id);

        if (eliminado) {
            request.getSession().setAttribute("mensaje", "Usuario eliminado exitosamente.");
        } else {
            request.getSession().setAttribute("mensaje", "No se pudo eliminar el usuario. Inténtalo nuevamente.");
        }
        // Redirigir a la página correspondiente
        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaCategorias");
    }

    private void generarExcelCategorias(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> categorias = dao.listarCategorias();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=categorias.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Categorías");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row headerRow = sheet.createRow(0);
            String[] columnas = {"ID", "Nombre"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (Categoria categoria : categorias) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(categoria.getIdCategoria());
                row.createCell(1).setCellValue(categoria.getNombre());
            }

            workbook.write(response.getOutputStream());
        }
    }

    private void agregarMarca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String url_imagen = request.getParameter("url_imagen");

        // Crear un objeto Marca con los datos del formulario
        Marca marca = new Marca();
        marca.setNombre(nombre);
        marca.setUrl_imagen(url_imagen);

        // Agregar la marca utilizando el DAO
        boolean registrado = new MarcaDAO().agregarMarca(marca);

        if (registrado) {
            request.setAttribute("mensaje", "Categoria registrado exitosamente.");
        } else {
            request.setAttribute("mensaje", "Error al registrar la categoria.");
        }

        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaMarcas");
    }

    private void eliminarMarca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMarca = Integer.parseInt(request.getParameter("idMarca"));
        boolean eliminado = new MarcaDAO().eliminarMarca(idMarca);
        if (eliminado) {
            request.getSession().setAttribute("mensaje", "Usuario eliminado exitosamente.");
        } else {
            request.getSession().setAttribute("mensaje", "No se pudo eliminar el usuario. Inténtalo nuevamente.");
        }
        // Redirigir a la página correspondiente
        response.sendRedirect(request.getContextPath() + "/ControladorAdmin?accion=paginaMarcas");
    }

    private void generarExcelMarcas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de marcas desde el DAO
        List<Marca> marcas = new MarcaDAO().listarMarcas();  // Suponiendo que tienes un DAO que obtiene las marcas

        // Configurar la respuesta HTTP para que el navegador lo trate como un archivo Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=marcas.xlsx");

        // Crear el libro de trabajo Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Marcas");

            // Crear una fuente para el título
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para la fila del título
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Crear la fila para el título de la tabla
            Row titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("Lista de Marcas");
            titleRow.getCell(0).setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); // Combinar celdas para el título

            // Crear una fuente para los encabezados
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            // Estilo para los encabezados de las columnas
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex()); // Morado
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            // Estilo para las celdas de datos
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setAlignment(HorizontalAlignment.CENTER);

            // Definir los encabezados de las columnas
            String[] columnas = {"ID", "Nombre", "URL Imagen"};

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(1);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // Llenar los datos de las marcas
            int rowIndex = 2; // Los datos empiezan después de la fila de encabezado
            for (Marca marca : marcas) {
                Row row = sheet.createRow(rowIndex++);
                createCell(row, 0, String.valueOf(marca.getIdMarca()), dataStyle);
                createCell(row, 1, marca.getNombre(), dataStyle);
                createCell(row, 2, marca.getUrl_imagen(), dataStyle);
            }

            // Ajustar automáticamente las columnas
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo Excel al OutputStream (respuesta HTTP)
            workbook.write(response.getOutputStream());
        }
    }

}
