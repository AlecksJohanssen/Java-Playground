#include <stdio.h>
#include <iostream>
using namespace std;

class List {
  struct Node {
    int data;
    Node * next;
  };
  int count;
  Node * head;
  public:
  List() {
    head = NULL;
  }
  ~List() {
    printf("%d current data", head -> data);
    while(head != NULL) {
      Node * n = head -> next;
      delete head;
      head = n;
      if( head == NULL ) {
        printf("Head Null");
      } else {
        printf("%d \n", head-> data);
      }
    }
  }
  void add(int value) {
    Node * n = new Node;
    n -> data = value;
    n -> next = head;
    head = n;
    printf("Add First");
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
  
  void Print(Node* head)
  {
    while (head != NULL)
    {
      cout << head->data << endl;
      head = head->next;
    }
    cout << "NULL";
  }
};

int main() {
  List a;
  a.add(1);
  a.add(2000);
  a.add(12);
  a.add(100);
  a.add(1999);
  return 0;
}
