import java.util.*;

class MergeSort {
  public int[] numbers;
  public int[] helper;
  public int number;

  public void sort(int[] values) {
    this.numbers = values;
    number = values.length;
    this.helper = new int[number];
    mergesort(0, number - 1);
  }

  public void mergesort(int low, int high) {
    if ( low < high ) {
      int middle = low + ( high - low ) / 2;
      System.out.println(low);
      System.out.println(high);
      mergesort(low, middle);
      mergesort(middle + 1, high);
      merge(low, middle, high);
    }
  }
  public void merge(int low, int middle, int high) {
    for (int i = low; i <= high; i++ ) {
      helper[i] = numbers[i];
    }
    System.out.print(Arrays.toString(helper));
    int i = low;
    int j = middle + 1;
    int k = low;
    while ( i <= middle && j <= high ) {
      if( helper[i] <= helper[j] ) {
        numbers[k] = helper[i];
        i++;
      } else {
        numbers[k] = helper[j];
        j++;
      }
      k++;
    }
    while ( i <= middle ) {
      numbers[k] = helper[i];
      k++;
      i++;
    }
  }
}

class MSorts {
  public static final void main(String[] blah) {
    MergeSort ms = new MergeSort();
    int[] array = {6,10,100,19, 1,7,5,3};
    ms.sort(array);
    System.out.println(Arrays.toString(array));
  }
}
