
import java.util.*; 
class Sort {
  public static void shell(int[] a) {
    int increment = a.length / 2;
    System.out.print(a.length);
    while (increment > 0) {
      for (int i = increment; i < a.length; i++) {
        System.out.println(i);
        int j = i;
        int temp = a[i];
        while (j >= increment && a[j - increment] > temp) {
          a[j] = a[j - increment];
          System.out.println("Time triggered");
          j = j - increment;
        }
        a[j] = temp;
      }
      if (increment == 2) {
        increment = 1;
      } else {
        System.out.println("Pre-increment" + increment);
        increment *= (5.0 / 11);
        System.out.println("Post-increment" + increment);
      }
    }
  }
}

class sorts {
  public static final void main(String[] cmd_lineParams)  {
    Sort sort = new Sort();
    int[] array = {6,10,100,19, 23 , 1,7,5,3, 112, 19, 99, 45, 62};
    sort.shell(array);
    System.out.println(Arrays.toString(array));
  }
}










