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

  void Print() {
    Node * node = head;
    while (node != NULL)
    {
      cout << node->data << endl;
      node = node ->next;
    }
    cout << "NULL";
  }

  void deleteNode(int params) {
    Node * currentNode = head;
    Node * prevNode;
    if (currentNode -> data == params && currentNode != NULL) {
      printf("Found");
      head = currentNode -> next;
      free(currentNode);
      return;
    }
    while (currentNode -> data != params && currentNode != NULL) {
      prevNode = currentNode;
      printf("%d", prevNode -> data);
      currentNode = currentNode -> next;
    }
    if (currentNode == NULL) {
      printf("trigger");  
      return;
    } else {
      prevNode -> next = currentNode -> next;
      free(currentNode);
    }
  } 
};

int main() {
  List a;
  a.add(1);
  a.add(2000);
  a.add(12);
  a.add(100);
  a.add(1999);
  a.deleteNode(2000);
  a.Print();
  return 0;
}
