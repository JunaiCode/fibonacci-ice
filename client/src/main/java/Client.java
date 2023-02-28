import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws UnknownHostException {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway.ice_twoway();

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }

            getData(printer);
            /*printer.printString("Hello World!");
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();
            printer.fibonacci(number);*/

        }
    }
    public static void getData(Demo.PrinterPrx printer) throws UnknownHostException {
        Scanner sc = new Scanner(System.in);
        int numero;
        String hostname = Inet4Address.getLocalHost().getHostName();

        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingresa un número: ");
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                int resultado = printer.fibonacci(numero,hostname);
                System.out.println("El termino " + numero + " ésimo de la serie es: " + resultado);
            } else {
                if(sc.next().equals("exit")){
                    continuar = false;
                }else{
                    System.out.println("Entrada inválida, intenta de nuevo");
                }

            }
        }
    }
}