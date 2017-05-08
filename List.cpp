#include <stdio.h>


class List {
  struct Node {
    int data;
    Node * next;
  };
  Node * head;
  public:
  List() {
    head = NULL;
  }
  ~List() {
    while(head != NULL) {
      Node * n = head -> next;
      delete head;
      head = n;
    }
  }
  void add(int value) {
    Node * n = new Node;
    n -> data = value;
    n -> next = head;
    head = n;
  }

  int size() {
    int count;
    Node* tail = head;
    while(tail != NULL) {
      count ++;
      tail = tail -> next;
    }
    return count;
  }
};

int main() {
  List a;
  a.add(1);
  printf("%d", a.size());
  return 0;
}
