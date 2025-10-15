/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Bryan
 */
public class EnvioWhatsApp {

//    public static final String ACCOUNT_SID = "";
//    public static final String AUTH_TOKEN = "";
//    public static void enviarMensaje(String numeroDestino, String mensajeTexto) {
//        try {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//            Message message = Message.creator(
//                    new PhoneNumber("whatsapp:" + numeroDestino), // número del cliente
//                    new PhoneNumber("whatsapp:+14155238886"), // número oficial de Twilio
//                    mensajeTexto
//            ).create();
//
//            System.out.println("Mensaje enviado con SID: " + message.getSid());
//        } catch (Exception e) {
//            System.out.println("Error al enviar mensaje: " + e.getMessage());
//        }
//    }
    //public String phoneNumberId = "";
    //public String accessToken = "";
    

    public void enviarMensajeWhatsApp(String numeroDestino, String mensaje) {
    String phoneNumberId = Enviroment.phoneNumberId; // Tu Phone Number ID de Meta
    String accessToken = Enviroment.accessToken; // Tu token válido

    try {
        // URL de la API WhatsApp Cloud
        URL url = new URL("https://graph.facebook.com/v17.0/" + phoneNumberId + "/messages");

        // Configuración de conexión
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Crear JSON del mensaje
        String json = """
        {
          "messaging_product": "whatsapp",
          "to": "%s",
          "type": "text",
          "text": {"body": "%s"}
        }
        """.formatted(numeroDestino, mensaje);

        // Enviar mensaje (UTF-8)
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes("UTF-8"));
        }

        // Código HTTP
        int responseCode = conn.getResponseCode();
        System.out.println("Mensaje enviado = Codigo HTTP: " + responseCode);

        // Leer respuesta completa del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("Respuesta del servidor: " + response.toString());

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al enviar mensaje: " + e.getMessage());
    }
}


}

//String numeroCliente = "593984055114"; // número del cliente (sin el 0 inicial)
//String mensaje = "Hola Bryan, tu pago fue registrado exitosamente. ¡Gracias por confiar en nosotros!";
//
//EnvioWhatsApp.enviarMensaje(numeroCliente, mensaje);
