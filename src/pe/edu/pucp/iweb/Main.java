package pe.edu.pucp.iweb;

import pe.edu.pucp.iweb.Excepciones.NetworkException;
import pe.edu.pucp.iweb.Excepciones.TokenException;
import pe.edu.pucp.iweb.Token;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NetworkException, TokenException {

        Scanner scanner = new Scanner(System.in);
        //Ingresar la lista de tokenBaneados y empresasBaneadas
        String[] tokenBaneados = {"storeum", "caf", "wkahf", "pincoin", "ifan","stox","storiqa"};

        //Ingresar la lista de redes permitidas
        String[] networkPermitidas = {"ethereum mainnet","ropsten test network","kovan test network",
                "matic mainnet","smart chain"};

        System.out.println("\n***********************Bienvenido a Telecripto************************\n");

        Token token = new Token();

        Boolean fin = false;
        while (!fin) {
            String menu = "\nElige la acción que desea realizar:\n" +
                    "1. Registrar un token\n" +
                    "2. Resumen de datos ingresados \n" +
                    "3. Publicar token\n" +
                    "4. Salir\n" +
                    "Opción: ";
            System.out.print(menu);

            try{
                int opcion = Integer.valueOf(scanner.nextLine());

                if( opcion >= 1 && opcion <= 4){
                    switch (opcion){
                        case 1:
                            String nombreDelToken = Main.validarToken(tokenBaneados);

                            double precio;
                            while(true){
                                System.out.print("Ingrese el precio de lanzamiento (usd): ");
                                String precioStr = scanner.nextLine();

                                try{
                                    precio = Double.parseDouble(precioStr);
                                    break;
                                }catch (NumberFormatException h){
                                    System.out.println("Precio invalido");
                                }
                            }

                            double volumen;
                            while(true){
                                System.out.print("Ingrese el volumen total de token minado (millones): ");
                                String volumenStr = scanner.nextLine();

                                try{
                                    volumen = Double.parseDouble(volumenStr);
                                    break;
                                }catch (NumberFormatException g){
                                    System.out.println("Volumen invalido");
                                }
                            }

                            String nombreDeLaRed = Main.validarNetwork(networkPermitidas);

                            token.setNombre(nombreDelToken);
                            token.setPrecioInicial(precio);
                            token.setVolumen(volumen);
                            token.setNetwork(nombreDeLaRed);

                            System.out.println("**************************  Registro exitoso del token    **************************");
                            break;

                        case 2:
                            if((token.getNombre() == null) && (token.getPrecioInicial() == 0.0) && (token.getVolumen() == 0.0) && (token.getNetwork() == null) ){
                                System.out.println("No hay datos");
                            }
                            else {
                                System.out.println("**************************  RESUMEN DEL TOKEN   **************************");
                                System.out.println("Token : " + token.getNombre());
                                System.out.println("Precio Inicial : " + token.getPrecioInicial() + " usd");
                                System.out.println("Volumen : " + token.getVolumen() + " millones");
                                System.out.println("Network : " + token.getNetwork());
                                System.out.println("**************************************************************************");
                            }

                            break;
                        case 3:
                            if((token.getNombre() == null) && (token.getPrecioInicial() == 0.0) && (token.getVolumen() == 0.0) && (token.getNetwork() == null) ){
                                System.out.println("No hay ningun token para publicar");
                            }
                            else{
                                String palabraGenerada = Main.GenerarPalabra();
                                System.out.println("El smart contract de la red es: "+ palabraGenerada);

                            }

                            break;
                        case 4:
                            System.out.println("Gracias por usar el sistema");
                            fin = true;
                            break;

                    }

                }
                else{
                    System.out.println("No existe la opcion , intente denuevo");
                }

            }catch (NumberFormatException e){
                System.out.println("Opcion invalida");
            }

        }

    }

    public static String validarToken(String[] tokenBaneados) throws TokenException {
        Scanner sc = new Scanner(System.in);
        String token;
        while(true){
            System.out.print("Ingrese el nombre del token:" );
            token = sc.nextLine();

            try{
                for(String tokens : tokenBaneados){
                    if(tokens.equals(token)){
                        throw  new TokenException();
                    }
                }
                break;
            }catch (TokenException m){
                System.out.println(m.getMessage());
            }
        }
        return token;

    }

    public static String validarNetwork(String[] networkPermitidas) throws NetworkException {
        Scanner sc = new Scanner(System.in);
        String red;
        while(true){
            System.out.print("Ingrese el nombre de la red distribuida: ");
            red = sc.nextLine();

            try{
                boolean coincidencia = false;
                for(String redes : networkPermitidas){
                    if(redes.equals(red)){
                        coincidencia = true;
                        break;
                    }
                }

                if(coincidencia){
                    break;
                }
                else{
                    throw  new NetworkException();
                }

            }catch (NetworkException n){
                System.out.println(n.getMessage());
            }

        }
        return red;



    }

    public static String GenerarPalabra(){
        String palabra = "";
        //int caracteres = (int)(Math.random()*20)+2;
        int caracteres = 20;
        for (int i=0; i<caracteres; i++){
            int codigoAscii = (int)Math.floor(Math.random()*(122 - 97)+97);
            palabra = palabra + (char)codigoAscii;
        }
        return palabra;
    }

}
