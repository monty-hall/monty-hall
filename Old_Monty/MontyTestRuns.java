import java.util.*;

public class MontyTestRuns {

    public static final int REGULAR = 1;
    public static final int EVIL = 2;
    public static final int ANGELIC = 3;
    public static final int PREPARED = 4;
    public static final int PROBABILISTIC = 5;
    public static final int RANDOM = 6;


    public static void main(String[] args) {


        Random rand = new Random(System.currentTimeMillis());
        //taking inputs
        System.out.println("Choose Monty: ");
        Scanner scanner = new Scanner(System. in);
        String type = scanner. nextLine();
        System.out.println("How many trials? ");
        Scanner num = new Scanner(System.in);
        int n = Integer.parseInt(num.next());
        if(type.equals("regular")) {
            System.out.println("Input random, switch, or stay");
            Scanner scan = new Scanner(System. in);
            String method = scan. nextLine();
            int wins = 0;
            int i = 0;
            int[][][][] data = new int[3][2][2][2];
            while( i < n) {
                int prize = rand.nextInt(3) + 1;
                int chose = rand.nextInt(3) + 1;
                int result = chose;

                while (result == chose || result == prize) {
                    result = rand.nextInt(3) + 1;
                }
                int final_chose = result;
                while(final_chose == result) {
                    final_chose = rand.nextInt(3) + 1;
                }
                if(method.equals("stay")) {
                    final_chose = chose;
                }
                if(method.equals("switch")) {
                    while (final_chose == chose || final_chose == result) {
                        final_chose = rand.nextInt(3) + 1;
                    }
                }
                if(final_chose == prize) {
                    wins++;
                }
                i++;
            }
            double percent_win = (double)wins * 100/(double)n;
            System.out.println("Player wins " + percent_win + "% of the time.");
        }

        else if(type.equals("hell")) {
            System.out.println("Input random, switch, or stay");
            Scanner scan = new Scanner(System. in);
            String method = scan. nextLine();
            int wins = 0;
            int i = 0;
            while( i < n) {
                int prize = rand.nextInt(3) + 1;
                int chose = rand.nextInt(3) + 1;
                int result = chose;
                if(prize == chose) {
                    while (result == chose || result == prize) {
                        result = rand.nextInt(3) + 1;
                    }
                    int final_chose = result;
                    while(final_chose == result) {
                        final_chose = rand.nextInt(3) + 1;
                    }
                    if(method.equals("stay")) {
                        final_chose = chose;
                    }
                    if(method.equals("switch")) {
                        while (final_chose == chose || final_chose == result) {
                            final_chose = rand.nextInt(3) + 1;
                        }
                    }
                    if(final_chose == prize) {
                        wins++;
                    }
                }
                i++;
            }
            double percent_win = (double)wins * 100/(double)n;
            System.out.println("Player wins " + percent_win + "% of the time.");
        }

        else if(type.equals("angelic")) {
            System.out.println("Input random, switch, or stay");
            Scanner scan = new Scanner(System. in);
            String method = scan. nextLine();
            int wins = 0;
            int i = 0;
            while( i < n) {
                int prize = rand.nextInt(3) + 1;
                int chose = rand.nextInt(3) + 1;
                int result = chose;
                if(prize == chose) {
                    wins++;
                }
                if(prize != chose) {
                    while (result == chose || result == prize) {
                        result = rand.nextInt(3) + 1;
                    }
                    int final_chose = result;
                    while(final_chose == result) {
                        final_chose = rand.nextInt(3) + 1;
                    }
                    if(method.equals("stay")) {
                        final_chose = chose;
                    }
                    if(method.equals("switch")) {
                        while (final_chose == chose || final_chose == result) {
                            final_chose = rand.nextInt(3) + 1;
                        }
                    }
                    if(final_chose == prize) {
                        wins++;
                    }
                }
                i++;
            }
            double percent_win = (double)wins * 100/(double)n;
            System.out.println("Player wins " + percent_win + "% of the time.");
        }




    }
}
