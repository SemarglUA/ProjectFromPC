package Threads.MyGoodCoding;

import java.util.LinkedList;
import java.util.List;

public class GardenWithThreads {
    public static volatile List<String> fruit = new LinkedList<String>();
    public static volatile List<String> vegatable = new LinkedList<String>();


    public static void main(String args[]){
        Garden garden = new Garden();

        for(int i = 0; i < 20; i++){
            fruit.add("free cell");
            vegatable.add("free cell");
        }
        String[] fruit_1 = {"Apple", "Orange"};
        String[] vegatable_1 = {"Melon", "Eggplant", "Beet"};

        String[] fruit_2 = {"Apple_2", "Orange_2"};
        String[] vegatable_2 = {"Melon_2", "Eggplant_2", "Beet_2"};

        Thread firstGardener = new Thread(new GardenThread(fruit_1, 5, vegatable_1, 4 , garden));
        Thread secondGardener = new Thread(new GardenThread(fruit_2, 2, vegatable_2, 7 , garden));

        try {
            firstGardener.start();
            secondGardener.start();
            firstGardener.join();
            secondGardener.join();
        }catch(InterruptedException e){
            System.out.println("Interrupted in main thread");
        }

        garden.removeFruit(5);
        garden.removeVegatable(8);

        System.out.println();
        System.out.println();

        for(String s: fruit)
            System.out.print(s + " ");

        System.out.println();
        System.out.println();


        for(String s: vegatable)
            System.out.print(s + " ");


    }

    public static class Garden{
        Garden(){

        }

        public void addFruit(int index, String fruit){
            synchronized (GardenWithThreads.fruit) {
                GardenWithThreads.fruit.add(index, fruit);
            }
        }

        public void removeFruit(int index){
            synchronized (fruit){
                fruit.remove(index);
            }

        }

        public void addVegatable(int index, String vegatable){
            synchronized (GardenWithThreads.vegatable){
                GardenWithThreads.vegatable.add(index, vegatable);
            }
        }

        public void removeVegatable(int index){
            synchronized (vegatable){
                vegatable.remove(index);
            }

        }

    }

    public static class GardenThread implements Runnable{
        private int possition = 0;
        private Garden garden;
        private int begginFruit, endFruit, begginVegatable, endVegatable;
        private String[] addFruit, addVegatable;
//        List<String> fruit = new ArrayList<String>();
//        List<String> vegatable = new ArrayList<String>();

        GardenThread(String[] addFruit, int begginFruit, String[] addVegatable, int begginVegatble,  Garden garden){
            this.begginFruit = begginFruit;
            endFruit = begginFruit + addFruit.length - 1;
            this.addFruit = addFruit;
            this.begginVegatable = begginVegatble;
            endVegatable = begginVegatble + addVegatable.length - 1;
            this.addVegatable = addVegatable;
            this.garden = garden;
        }


        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + " начал работу в саду.");

            System.out.println("Начинаем закидывать фрукты");
            for (int i = begginFruit; i <= endFruit; i++){
                garden.addFruit(i, addFruit[possition++]);
            }

            System.out.println("Начинаем закидывать овощи");
            possition = 0;
            for(int i = begginVegatable; i <= endVegatable; i++ ){
                garden.addVegatable(i, addVegatable[possition++]);
            }

        }
    }
}