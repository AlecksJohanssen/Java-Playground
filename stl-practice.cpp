#include <stdio.h>
#include <vector>

using namespace std;
int main() {
  vector<int> vect;
  int i = 100;
  for (int a = 0; a < i; a++) {
    vect.push_back(a);
  }
  printf("%lu", vect.size());
  return 0; 
}
