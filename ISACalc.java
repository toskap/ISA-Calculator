import java.util.Scanner; 
public class ISACalc{

    public static double troposphere(double h, String quantity){
        double T = 288.15 - 0.0065 * h;
        double p = Math.pow(T/288.15, -9.80665/(-0.0065*287))*101325;
        double rho = p/(287*T);
        switch(quantity) {
            case "T":
              return T;
              case "p":
              return p;
            case "rho":
                return rho;
            default:
              return 0.0;
          }
    }
    
    public static double stratosphere(double h, String quantity){
        double T = troposphere(11000, "T");
        double p = Math.exp(-9.80665*(h-11000)/(287*T))*troposphere(11000, "p");
        double rho = p/(287*T);
        switch(quantity) {
            case "T":
              return T;
            case "p":
              return p;
            case "rho":
                return rho;
            default:
              return 0.0;
          }
    }

    public static double mesosphere(double h, String quantity){
        double T = stratosphere(20000, "T") + 0.001 * (h - 20000);
        double p = Math.pow(T/troposphere(11000, "T"), -9.80665/(0.001*287))*stratosphere(20000, "p");
        double rho = p/(287*T);
        switch(quantity) {
            case "T":
              return T;
              case "p":
              return p;
            case "rho":
                return rho;
            default:
              return 0.0;
          }
    }

    public static double thermosphere(double h, String quantity){
        double T = mesosphere(32000, "T") + 0.0028 * (h - 32000);
        double p = Math.pow(T/mesosphere(32000, "T"), -9.80665/(0.0028*287))*mesosphere(32000, "p");
        double rho = p/(287*T);
        switch(quantity) {
            case "T":
              return T;
              case "p":
              return p;
            case "rho":
                return rho;
            default:
              return 0.0;
          }
    }

    //lapse rate of exosphere taken to be 0 K/m
    public static double exosphere(double h, String quantity){
        double T = thermosphere(47000, "T");
        double p = Math.exp(-9.80665*(h-47000)/(287*T))*thermosphere(47000, "p");
        double rho = p/(287*T);
        switch(quantity) {
            case "T":
              return T;
            case "p":
              return p;
            case "rho":
                return rho;
            default:
              return 0.0;
          }
    }
    
    public static double temperature(double h){
        double T = 288.15;
        if(h<=11000){
            T = troposphere(h, "T");
        } else if(h>11000 && h<=20000){
            T = stratosphere(h, "T");
        } else if(h>20000 && h<=32000){
            T = mesosphere(h, "T");
        } else if(h>32000 && h<=47000){
            T = thermosphere(h, "T");
        } else{
            T = exosphere(h, "T");
        }
        return T;
    }
  
    public static double pressure(double h){
        double p = 288.15;
        if(h<=11000){
            p = troposphere(h, "p");
        } else if(h>11000 && h<=20000){
            p = stratosphere(h, "p");
        } else if(h>20000 && h<=32000){
            p = mesosphere(h, "p");
        } else if(h>32000 && h<=47000){
            p = thermosphere(h, "p");
        } else{
            p = exosphere(h, "p");
        }
        return p;
    }

    public static double density(double h){
        double rho = 288.15;
        if(h<=11000){
            rho = troposphere(h, "rho");
        } else if(h>11000 && h<=20000){
            rho = stratosphere(h, "rho");
        } else if(h>20000 && h<=32000){
            rho = mesosphere(h, "rho");
        } else if(h>32000 && h<=47000){
            rho = thermosphere(h, "rho");
        } else{
            rho = exosphere(h, "rho");
        }
        return rho;
    }
  
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter altitude ");
        Double h = obj.nextDouble();
        Scanner obj2 = new Scanner(System.in);
        System.out.println("What would you like to calculate? (Enter 'T', 'p', or 'rho'): ");
        String type = obj2.nextLine();

        double output = 0;
        if(type.equals("T")) output = temperature(h);
        if(type.equals("p")) output = pressure(h);
        if(type.equals("rho")) output = density(h);

        System.out.println(output);
    }
}