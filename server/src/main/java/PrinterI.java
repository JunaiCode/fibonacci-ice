public class PrinterI implements Demo.Printer
{
    public void printString(String s, com.zeroc.Ice.Current current)
    {
        System.out.println(s);
    }

    public  int fibonacci(int a,String host, com.zeroc.Ice.Current current){
        int d = 0, b = 1, c;
        System.out.println("Host: " + host);
        for (int i = 0; i < a; i++) {
            System.out.println(d);
            c = d + b;
            d = b;
            b = c;
        }
        return d;
    }
}