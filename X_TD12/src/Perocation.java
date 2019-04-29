
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
	  
	  
	  public static double montecarlo(int n)
	  {
		  double Sum = 0.0;
		  for (int i = 0; i < n; i++)
		  {
			  Sum += percolation();
		  }
		  return Sum/n;
	  }

	  public static void main(String[] args) {
		
		  int n = 100000;
		  System.out.printf("%.3f",montecarlo(n));
		  

	  } // fin de la fonction principale

}
