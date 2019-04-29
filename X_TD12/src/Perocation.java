
public class Perocation {

	static int size = 10;
	  static int length = size * size;
	  static boolean[] grid = new boolean[length];
	  
//	  public static void print_co(int n)
//	  {
//		  int a, b;
//		  a = n % size;
//		  b = n / size;
//		  System.out.println("( " + b + ", " +a+ " )");
//	  }
	  
	  public static void init()
	  {
		  for(int i = 0 ; i < length; i++)
		  {
			  grid[i] = false;
		  }
	  }

	  public static void print()
	  {
		  for(int i = 0 ; i < size; i++)
		  {
			  for(int j = 0; j < size; j++)
			  {
				  if (grid[i*size + j] == true)
				  {
					  System.out.print("-");
				  }
				  else
				  {
					  System.out.print("*");
				  }
			  }
			  System.out.print("\n");
		  }
	  }
	  
	  public static int randow_shadow()
	  {
		  int Ran = (int)(length * Math.random() );
		  //System.out.println(Ran);
		  grid[Ran] = true;
		  return Ran;
	  }
	  
	  public static int detect_path(boolean[] seen, int n , boolean up)
	  {
		  int dir = 1;
		  //Decide the direction;
		  if (up == false)
			 dir = -1;
		  
		  if ( n < size && dir == -1 )
		  {
			  //System.out.println("Arrived at the top");
			  return (n);
		  }
		  if ( n > length - size - 1 && dir == 1)
		  {
			  //System.out.println("Arrived at the bottom");
			  return (n);
		  }
		  
		  if ( n % size == 0)
		  {
			//System.out.println("Arrived at the left border") ;
			if (grid[n + dir * size] == true && seen[n+ dir *size] == false)
			{
				//System.out.println("Go up(down)");
				return (n + dir * size);
			}		
			if (grid[n + 1] == true && seen[n+1] == false)
			{
				//System.out.println("Go right");
				return (n + 1);
			}
			//System.out.println("Stacked");
			return -1;
			
		  }
		  
		  if ( (n+1) % size == 0)
		  { 
			if (grid[n + dir * size] == true && seen [n + dir*size] == false)
				{
					//System.out.println("Go up(down)");
					return (n + dir*size);
				}
			//System.out.println("Arrived at the right border");  
			if (grid[n - 1] == true && seen[n-1] == false)
			{
				//System.out.println("Go left");
				return (n - 1);
			}
			//System.out.println("Stacked");
			return -1;
		  }
		  if (grid[n + dir *size] == true && seen[n + dir*size] == false)
		  {
				//System.out.println("Go up(down)");
				return (n + dir*size);
			}

		  if (grid[n - 1] == true && seen[n-1] == false)
		  {
				//System.out.println("Go left");
				return (n - 1);
			}
		  if (grid[n+1] == true && seen[n+1] == false)
		  {
				//System.out.println("Go right");
				return (n + 1);
			}
		  		  //System.out.println("Stacked"); 
		return -1;
		 
		  
	  }
	    
	  public static boolean is_percolation(int n)
	  {
		  boolean[] seen = new boolean[length];
		  for(int i = 0 ; i< length ; i++)
		  {
			  seen[i] = false;
		  }
		  // Detection up;
		  boolean dir = false;
		  int next_step = n;
		  while (next_step >= size)
		  {
			  seen[next_step] = true;
			  next_step = detect_path(seen, next_step, dir);
			  //print_co(next_step);
			  if (next_step == -1)
			  {
				  return false;
			  }
		  }
		  
		  //Detection below
		  next_step = n;
		  dir = true;
		  while (next_step < length - size)
		  {
			  //System.out.println(2);
			  seen[next_step] = true;
			  //print_co(next_step);
			  next_step = detect_path(seen, next_step, dir);
			  if (next_step == -1)
			  {
				  return false;
			  }
		  }
		  
		  return true;
	  }
	  
	  public static double percolation()
	  {
		  init();
		  //print();
		  
		  int r = randow_shadow();
		  while(is_percolation(r) == false)
		  {
			  r = randow_shadow();
			  //print();
		  }
		  
		  int counter = 0 ;
		  
		  for(int i = 0; i < length; i++)
		  {
			  if (grid[i] == true)
			  {
				  counter++;
			  }
		  }
		  
		  double res = (double) counter / length;
		  
		  return res;
		  
	  }

	  public static boolean is_percolation1(int n)
	  {
		  for (int i = 0; i < size; i++)
		  {
			  for (int j = 0; j < size; j++)
			  {
				  if (Unionfind.find(i) == Unionfind.find(size*size - size + j))
					  return true;
			  }
		  }
		  return false;
	  }
	  
	  public static void propagate_union(int n)
	  {
		  if(n == 0)
		  {
			  if (grid[1] == true)
			  {
				  Unionfind.Union(1, 0);
				 // propagate_union(1);
			  }
			  if (grid[size] == true)
			  {
				  Unionfind.Union(size, 0);
//				  propagate_union(size);
			  }
			  return;
		  }
		  if(n == size - 1)
		  {
			  if (grid[size - 2] == true)
			  {
				  Unionfind.Union(size - 1, 0);
//				  propagate_union(size - 1);
			  }
			  if (grid[2*size - 1] == true)
			  {
				  Unionfind.Union(2*size - 1, size - 1);
//				  propagate_union(2*size - 1);
			  }
			  return;
		  }
		  if(n == size*size - size)
		  {
			  if (grid[size * size - 2* size] == true)
			  {
				  Unionfind.Union(size * size - 2* size, size * size - size);
//				  propagate_union(size * size - 2 * size);
			  }
			  if (grid[size*size - size + 1] == true)
			  {
				  Unionfind.Union(size * size - size + 1, size * size - size);
//				  propagate_union(size * size - size + 1);
			  }
			  return;
		  }
		  if(n == size * size - 1)
		  {
			  if (grid[size * size - size - 1] == true)
			  {
				  Unionfind.Union(size * size - size - 1, size * size - 1);
//				  propagate_union(size * size - size - 1);
			  }
			  if (grid[size * size - 2] == true)
			  {
				  Unionfind.Union(size * size - 2, size * size - 1);
//				  propagate_union(size * size - 2);
			  }
			  return;
		  }
		  if ( n < size)
		  {
			  //System.out.println("Arrived at the top");
			  if(grid[n-1] == true)
			  {
				  Unionfind.Union(n-1 ,n);
//				  propagate_union(n-1);
			  }
			  if(grid[n+1] == true)
			  {
				  Unionfind.Union(n+1 ,n);
//				  propagate_union(n + 1);
			  }
			  if(grid[n+size] == true)
			  {
				  Unionfind.Union(n+ size ,n);
//				  propagate_union(n + size);
			  }
			  return;
		  }
		  if ( n > length - size - 1 )
		  {
			  //System.out.println("Arrived at the bottom");
			  if(grid[n-1] == true)
			  {
				  Unionfind.Union(n-1 ,n);
//				  propagate_union(n-1);
			  }
			  if(grid[n+1] == true)
			  {
				  Unionfind.Union(n+1 ,n);
//				  propagate_union(n + 1);
			  }
			  if(grid[n-size] == true)
			  {
				  Unionfind.Union(n-size ,n);
//				  propagate_union(n-size);
			  }
			  return;
		  }
		  
		  if ( n % size == 0)
		  {
			  //System.out.println("Arrived at the left bord");
			  if(grid[n-size] == true)
			  {
				  Unionfind.Union(n-size ,n);
//				  propagate_union(n-size);
			  }
			  if(grid[n+1] == true)
			  {
				  Unionfind.Union(n+1 ,n);
//				  propagate_union(n + 1);
			  }
			  if(grid[n+size] == true)
			  {
				  Unionfind.Union(n+ size ,n);
//				  propagate_union(n + size);
			  }
			  return;
		  }
		  
		  if ( (n+1) % size == 0)
		  { 
			//System.out.println("Arrived at the right bord");
			  if(grid[n-1] == true)
			  {
				  Unionfind.Union(n-1 ,n);
//				  propagate_union(n-1);
			  }
			  if(grid[n - size] == true)
			  {
				  Unionfind.Union(n-size ,n);
//				  propagate_union(n-size);
			  }
			  if(grid[n+size] == true)
			  {
				  Unionfind.Union(n+ size ,n);
//				  propagate_union(n + size);
			  }
			  return;
		  }
		  if(grid[n-1] == true)
		  {
			  Unionfind.Union(n-1 ,n);
//			  propagate_union(n-1);
		  }
		  if(grid[n+1] == true)
		  {
//			  Unionfind.Union(n+1 ,n);
			  propagate_union(n + 1);
		  }
		  if(grid[n+size] == true)
		  {
			  Unionfind.Union(n+ size ,n);
//			  propagate_union(n + size);
		  }
		  if(grid[n-size] == true)
		  {
			  Unionfind.Union(n-size, n);
//			  propagate_union(n + size);
		  }
		  return;
		  
	  }
	  
	  public static double percolation1()
	  {
		  init();
		  Unionfind.init();
		  Unionfind.init_hauteur();
		  
		  int r = randow_shadow();
		  while(is_percolation1(r) == false)
		  {
			  propagate_union(r);
			  r = randow_shadow();
//			  Unionfind.print();
//			  Unionfind.print_hauteur();
//			  print();
		  }
//		  Unionfind.print();
//		  print();
		  int counter = 0;
		  
		  for(int i = 0; i < length; i++)
		  {
			  if (grid[i] == true)
			  {
				  counter++;
			  }
		  }
		  
		  double res = (double) counter / length;
		  
		  return res;
		  
	  }
	  
	  public static double montecarlo(int n)
	  {
		  double Sum = 0.0;
		  for (int i = 0; i < n; i++)
		  {
			  Sum += percolation1();
		  }
		  return Sum/n;
	  }

	  public static void main(String[] args) {
		
		  System.out.println(montecarlo(100000));
		  
	  } // fin de la fonction principale

}
