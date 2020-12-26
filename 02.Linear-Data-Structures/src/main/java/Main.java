import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> integers=new ArrayDeque<>();

        //сума на числа
       // System.out.println(sum(5));

        //фибоначи
       // int fibs=fibs(5);
       // System.out.println(fibs);




        //факториел
       //int[]sum=new int[1];
       //int number=sum[0];
       //int result=factorial(3);
       //System.out.println(result);

    }

    //събиране на сума от числа
   //public static int sum(int n){
   //    if (n==0){
   //        return 0;
   //    }else {
   //        return n+sum(n-1);
   //    }
   //}

    //фибоначи
   // public static int fibs(int n){
   //     if (n==1 || n==2){
   //         return 1;
   //     }else {
   //         return fibs(n-1)  +  fibs(n-2);
   //         //рекурсията дълбай в първото извикване
   //         //когато върне 1 от условието горе
   //         //се връща крачка назад (ако n=2 -> n=3)
   //         //щом е влезнала в проверката, се мести
   //         //на второто извикване на рекурсията
   //         //когато влезе отново в проверката,
   //         //отново се изиква първата рекурсия
   //         //крайният резултат е събираме числото 1 на всяко влизане в проверката
   //     }
   // }

    //факториел
   //public static int factorial(int n){
   //    if (n==1){
   //        return 1;
   //    }else {
                //връща като резултат n*  тогава влиза в рекурсията
   //        return n*   factorial(n-1);
   //    }
   //}
}
