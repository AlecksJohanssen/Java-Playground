import java.util.*;
import java.lang.Math.*;
import java.lang.String.*;

public class AVLTree<AnyType extends Comparable<? super AnyType>> {
  public AVLTree() {
    root = null;
  }
  public static class AvlNode<AnyType> {
    AvlNode(AnyType elem) {
      this(elem, null, null);
    }

    AvlNode( AnyType elem, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
      element = elem;
      left = lt;
      right = rt;
      height = 0;
    }

    AnyType element;
    AvlNode<AnyType> left;
    AvlNode<AnyType> right;
    int height;
  }
  private  AvlNode<AnyType> root;
  public void insert( AnyType x) {
    root = insert ( x, root);
  }
  public void printTree( )
    {
            printTree( root );
    }
  private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t) {
    if(t == null) {
      return new AvlNode<AnyType>(x, null, null);
    }
    int compareResult = x.compareTo(t.element);
    if(compareResult < 0) {
      t.left = insert(x, t.left);
      if( height(t.left) - height(t.right) == 2) {
        if(x.compareTo(t.left.element) < 0) {
          t = rotateWithLeftChild(t);
        } else {
					t = doubleWithLeftChild(t);
				}
      } else if( compareResult > 0) {
				t.right = insert(x, t.right);
				if(height(t.right) - height(t.left) == 2) {
					if(x.compareTo(t.right.element) > 0) {
						t = rotateWithRightChild(t);
					} else {
						t = doubleWithRightChild(t);
					}
				}
			} else {
      
      }
    }
    t.height = Math.max(height(t.left), height(t.right)) + 1;
    return t;
  }
  private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
    AvlNode<AnyType> k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;
    k2.height = Math.max( height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max( height(k1.left), k2.height ) + 1;
    return k1;
  }
  private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
  {
    AvlNode<AnyType> k2 = k1.right;
    k1.right = k2.left;
    k2.left = k1;
    k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
    k2.height = Math.max( height( k2.right ), k1.height ) + 1;
    return k2;
  }
  private int height(AvlNode<AnyType> t) {
    return t == null ? -1 : t.height;
  }
  private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
  {
    k3.left = rotateWithRightChild( k3.left );
    return rotateWithLeftChild( k3 );
  }

	private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
	{
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}
	public void printTree( AvlNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}
}
class tree {
	public static void main( String [ ] args )
	{
		AVLTree<Integer> t = new AVLTree<Integer>( );
		final int NUMS = 4000;
		final int GAP  =   37;

		System.out.println( "Checking... (no more output means success)" );

		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
			t.insert( i );

		if( NUMS < 40 )
			t.printTree( );
	}
}
