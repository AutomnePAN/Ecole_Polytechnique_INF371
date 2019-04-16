
public class Perocation {
	
	  static int size = 10;
	  static int length = size * size;
	  static boolean[] grid = new boolean[length];
	
	  
	  public static void print_co(int n)
	  {
		  int a, b;
		  a = n % size;
		  b = n / size;
		  System.out.println("( " + b + ", " +a+ " )");
	  }
	  
	  public static void init()
	  {
		  for(int i = 0 ; i < length; i++)
		  {
			  grid[i] = false;
		  }
	  }

	  public static void print(boolean[] g)
	  {
		  for(int i = 0 ; i < size; i++)
		  {
			  for(int j = 0; j < size; j++)
			  {
				  if (g[i*size + j] == true)
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
	  
	  public static void randow_shadow()
	  {
		  int Ran = (int)(length * Math.random() );
		  System.out.println(Ran);
		  grid[Ran] = true;
	  }
	  
	  public static int detect_path(boolean[] seen, int n , boolean up)
	  {
		  int dir = 1;
		  //Decide the direction;
		  if (up == false)
			 dir = -1;
		  
		  if ( n < size)
		  {
			  System.out.println("Arrived at the top");
			  return (n);
		  }
		  if ( n > length - size - 1)
		  {
			  System.out.println("Arrived at the bottom");
			  return (n);
		  }
		  
		  if ( n % size == 0)
		  {
			System.out.println("Arrived at the left border") ;
			if (grid[n + dir * size] == true && seen[n+ dir *size] == false)
			{
				System.out.println("Go up(down)");
				return (n + dir * size);
			}		
			if (grid[n + 1] == true && seen[n+1] == false)
			{
				System.out.println("Go right");
				return (n + 1);
			}
			System.out.println("Stacked");
			return -1;
			
		  }
		  
		  if ( (n+1) % size == 0)
		  { 
			if (grid[n + dir * size] == true && seen [n + dir*size] == false)
				{
					System.out.println("Go up(down)");
					return (n + dir*size);
				}
			System.out.println("Arrived at the right border");  
			if (grid[n - 1] == true && seen[n-1] == false)
			{
				System.out.println("Go left");
				return (n - 1);
			}
			System.out.println("Stacked");
			return -1;
		  }
		  if (grid[n + dir *size] == true && seen[n + dir*size] == false)
		  {
				System.out.println("Go uo(down)");
				return (n + dir*size);
			}

		  if (grid[n - 1] == true && seen[n-1] == false)
		  {
				System.out.println("Go left");
				return (n - 1);
			}
		  if (grid[n+1] == true && seen[n+1] == false)
		  {
				System.out.println("Go right");
				return (n + 1);
			}
		  		  System.out.println("Stacked"); 
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
			  print_co(next_step);
			  if (next_step == -1)
			  {
				  return false;
			  }
		  }
		  
		  //Detection below
		  dir = true;
		  next_step = n;
		  while (next_step < length - size)
		  {
			  seen[next_step] = true;
			  next_step = detect_path(seen, next_step, dir);
			  print_co(next_step);
			  if (next_step == -1)
			  {
				  return false;
			  }
		  }
		  
		  return true;
	  }

	  public static void main(String[] args) {
		
		  init();
		  print(grid);
		  for(int i = 0; i< 200 ; i++)
		  {
			  randow_shadow();
		  }
		  print(grid);
		  boolean test = is_percolation(45);
		  System.out.print(test);

		  

	  } // fin de la fonction principale

	
}
