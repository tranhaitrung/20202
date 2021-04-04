public class t {
    private int old;
    public int add(int a, int b){
        return a+b;
    }

    public double add(double a, double b){
        return a+b;
    }

    public static final double PI = 3.14;

    public static int td=101;

    public static double mul(int a){
        try{
            return a*PI;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("có qua");
        }
        System.out.println("Như nào");
        return a;
    }


    public static void main(String[] args) {

        double k = mul(10);
        System.out.println(k);
        td++;
        System.out.println(td);
    }
}
