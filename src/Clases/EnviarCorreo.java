package Clases;
import java.io.File;
import java.net.URLEncoder;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class EnviarCorreo {

    public static void enviarCorreo(String nombreProveedor, String idProducto, String nombreProducto, String cantidad, String motivo, String rutaImagen) {
        final String correoRemitente = "calebayala777@gmail.com";
        final String contrasenaApp = "gvkpqjvblomjmsse";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoRemitente, contrasenaApp);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(correoRemitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("axellxavierrr@gmail.com"));
            mensaje.setSubject("Solicitud de Devolución de Producto");

            String linkFormulario = generarLinkFormulario(nombreProducto, nombreProveedor);
            
            // Cuerpo del mensaje en HTML con botón
            String cuerpoHTML = "<html><body style='font-family: Arial, sans-serif;'>"
                    + "<p>Estimado proveedor <strong>" + nombreProveedor + "</strong>,</p>"
                    + "<p>Se solicita la devolución del siguiente producto:</p>"
                    + "<ul>"
                    + "<li>Nombre: " + nombreProducto + "</li>"
                    + "<li>Cantidad dañada: " + cantidad + "</li>"
                    + "<li>Motivo: " + motivo + "</li>"
                    + "</ul>"
                    + "<p>Por favor, haga clic en el siguiente botón para completar el formulario:</p>"
                    + "<a href='" + linkFormulario + "' style='"
                    + "background-color: #4CAF50;"
                    + "color: white;"
                    + "padding: 14px 25px;"
                    + "text-align: center;"
                    + "text-decoration: none;"
                    + "display: inline-block;"
                    + "border-radius: 5px;"
                    + "border: none;"
                    + "cursor: pointer;'>Acceder al Formulario</a>"
                    + "<p>Gracias por su pronta respuesta.</p>"
                    + "</body></html>";

            // Parte HTML del mensaje
            MimeBodyPart textoParte = new MimeBodyPart();
            textoParte.setContent(cuerpoHTML, "text/html; charset=utf-8");

            // Parte de la imagen adjunta
            MimeBodyPart imagenParte = new MimeBodyPart();
            File archivoImagen = new File(rutaImagen);
            if (archivoImagen.exists()) {
                imagenParte.attachFile(archivoImagen);
            } else {
                System.out.println("Imagen no encontrada en la ruta: " + rutaImagen);
            }

            // Crear multipart y añadir partes
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textoParte);
            multipart.addBodyPart(imagenParte);

            mensaje.setContent(multipart);
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generarLinkFormulario(String nombreProducto, String nombreProveedor) {
        try {
            String nombreCodificado = URLEncoder.encode(nombreProducto, "UTF-8");
            String proveedorCodificado = URLEncoder.encode(nombreProveedor, "UTF-8");
            
            return "https://docs.google.com/forms/d/e/1FAIpQLSenST6ervHLNxnQ2HGFvGr8bbOjYEFU7JQADYY9ANB7y8M8Zg/viewform"
                    + "?usp=pp_url&entry.1805938137=" + proveedorCodificado 
                    + "&entry.772008032=" + nombreCodificado;
        } catch (Exception e) {
            e.printStackTrace();
            return "Formulario no disponible.";
        }
    }
}