package exercise_1;

public class Stats <T extends Number> {
    private T[] arr;
    
    public Stats(T... arr) {
        this.arr = arr;
    }
    
    public double avg() {
        double sum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].doubleValue();
        }

        sum = sum / arr.length;

        return sum;
    }

    public boolean sameAvg(Stats<? extends Number> comparedStats) {
        if (Math.abs(this.avg() - comparedStats.avg()) >0.00001) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Stats<Integer> intStats = new Stats<>( 1 , 2 , 3 , 4 , 5 );
        Stats<Double> doubleStats = new Stats<>( 1.0 , 2.0 , 3.0 , 4.0 , 5.0 );
        if (intStats.sameAvg(doubleStats)) {
            System.out.println( "Средние значения равны" );
        } else {
            System.out.println( "Средние значения не равны" );
        }
    }
    
}
