package ie.tudublin;

public class Main
{
    public static void helloProcessing()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new HelloProcessing());
    }


    public static void helloProcessing2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new HelloProcessing2());
    }

    public static void bugZap()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BugZap());
    }

    public static void loops()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Loops());
    }

    public static void loops2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Loops2());
    }

    public static void arrays() 
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Arrays());
    }

    public static void arrays1() 
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Arrays1());
    }

    public static void life()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Life());
    }

    public static void life2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Life2());
    }

    public static void life3()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Life3());
    }

    public static void audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }

    public static void audio2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio2());
    }

    public static void audio3()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio3());
    }

    public static void colorfulLife()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ColorfulLife());
    }

    public void cat()
    {
        System.out.println("Hello world");

        Animal misty = new Animal("Misty");
        Animal lucy = new Animal("Lucy");

        lucy = misty;
        misty.setName("Tara");

        System.out.println(misty);
        System.out.println(lucy);

        
        Cat cat = new Cat("Ginger");

        while(cat.getNumLives() > 0)
        {
            cat.kill();
        }
        cat.kill();

    }
    public static void main(String[] args)
    {
        // helloProcessing2();
        // colorfulLife();
        life();
        // life2();
        // life3();
        // bugZap();
        // loops();
        // loops2();
        // arrays1();
        // arrays();
        // audio1();
        // audio2();
        // audio3();
    }
}