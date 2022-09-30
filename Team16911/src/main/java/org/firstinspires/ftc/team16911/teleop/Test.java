package org.firstinspires.ftc.team16911.teleop;

public class Test
{
    public static void main(String[] args)
    {
        Book Iliad = new Book();
        Iliad.pages = 250;
        Iliad.author = "Homer";
        Iliad.title = "Iliad";
        System.out.println(Iliad.title + " has " + Iliad.pages + " pages");
    }
    public static int multiply(int a, int b)
    {
        return a * b;
    }
}
class Book
{
    int pages;
    String author;
    String title;
}
