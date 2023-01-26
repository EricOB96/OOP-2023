package ie.tudublin;

public class Cat 
{
    private int numLives;

    String name;
	
	public void setName(String name)
	{
		this.name = name;
	}
    public void setLives(int lives)
	{
		this.numLives = lives;
	}
	
	public Cat()
	{
	}
	
	public Cat(String name)
	{
	}

    public Cat(int lives)
    {

    }
	
	public void Kill()
	{
        while(numLives > 0)
		{
			System.out.println("Ouch!");
            numLives --;
		}

        if(numLives == 0)
        {
            System.out.println("Dead");
        }

    }
}
