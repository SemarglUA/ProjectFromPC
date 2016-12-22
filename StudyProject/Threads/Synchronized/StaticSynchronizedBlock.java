package Threads.Synchronized;

public class StaticSynchronizedBlock {

    public static class OurPresiden{
        static String name;
        static OurPresiden president;

        static {
            synchronized (OurPresiden.class) {
                president = new OurPresiden();
            }
        }

        OurPresiden(){
            String name = "Poroshenko";
            System.out.println(name);
            this.name = name;
        }

        static OurPresiden generate(){
            return president;
        }
        @Override
        public String toString(){
            return name;
        }
    }

    public static void main(String args[]){
        System.out.println(OurPresiden.generate());
    }

}
