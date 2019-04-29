
public class Unionfind {
	public static int []equiv = new int [Perocation.length];	
	public static int []hauteur = new int [Perocation.length]; 
	public static void init()
	{
		for (int i = 0; i < Perocation.length; i++)
		{
			equiv[i] = i;
		}
	}
	
	public static void init_hauteur()
	{
		for (int i = 0; i < Perocation.length; i ++)
		{
			hauteur[i] = 1;
		}
	}

	public static void Union(int x, int y)
	{
		if(hauteur[x] < hauteur[y])
		{
			equiv[find(x)] = find(y);
			hauteur[x] ++;
		}
		else
		{
			equiv[find(y)] = find(x);
			hauteur[y] ++;
		}
	}
	
	public static int find(int x)
	{
		int y = x;
		while(y != equiv[x])
		{
			equiv[y] = equiv[equiv[y]];
			y = equiv[y];
		}
		return y;
	}
	
	public static void print()
	{
		for(int i = 0 ; i < Perocation.size; i++)
		  {
			  for(int j = 0; j < Perocation.size; j++)
			  {
				 System.out.print(equiv[i * Perocation.size + j] + " ");
			  }
			  System.out.print("\n");
		  }
	}
	
	public static void print_hauteur()
	{
		for(int i = 0 ; i < Perocation.size; i++)
		  {
			  for(int j = 0; j < Perocation.size; j++)
			  {
				 System.out.print(hauteur[i * Perocation.size + j] + " ");
			  }
			  System.out.print("\n");
		  }
	}

}
