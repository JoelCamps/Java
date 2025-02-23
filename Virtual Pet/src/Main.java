public class Main {
    public static void main(String[] args) {
        int energy = (int) (Math.random() * (60 - 40 + 1) + 40.0);
        int fatigue = (int) (Math.random() * (60 - 40 + 1) + 40.0);
        int sleep = (int) (Math.random() * (60 - 40 + 1) + 40.0);
        int hygiene = (int) (Math.random() * (60 - 40 + 1) + 40.0);
        int weight = (int) (Math.random() * (60 - 40 + 1) + 40.0);
        int menu;
        int dogcat;
        Boolean exit, dead;

        exit = false;
        dead = false;

        do {
            System.out.print("Do you want a dog(1) or cat(2)? ");
            dogcat = Teclat.llegirInt();
            if (dogcat == 1 || dogcat == 2) {
                exit = true;
            } else {
                System.out.println("This value is incorrect.");
            }
        } while (!exit);

        System.out.print("What name do you want to give it? ");
        String name = Teclat.llegirString();

        if (dogcat == 1) {
            System.out.println("You have chosen a dog and its name is " + name);
        } else {
            System.out.println("You have chosen a cat and its name is " + name);
        }
        System.out.println(" ");

        do {
            System.out.println(" ");
            System.out.println("INTERACT WITH YOUR PET");
            System.out.println(" ");
            System.out.println("1. Sleep");
            System.out.println("2. Play");
            System.out.println("3. Eat");
            if (dogcat == 1) {
                System.out.println("4. Shower");
            } else {
                System.out.println("4. Self-cleaning");
            }

            System.out.println("0. Exit");
            System.out.println(name + " stats are: Energy: " + energy + ", Fatigue: " + fatigue + ", Sleep: " + sleep + ", Hygiene: " + hygiene + " and Weight: " + weight);
            System.out.print("Chose an option: ");
            menu = Teclat.llegirInt();

            exit = false;
            switch (menu) {
                case 0:
                    exit = true;
                case 1:
                    energy = energy + 15;
                    System.out.println("The energy has increased by 15 now is: " + energy);
                    fatigue = fatigue - 15;
                    System.out.println("The fatigue has decreased by 15 now is: " + fatigue);
                    if (dogcat == 1) {
                        sleep = sleep - 25;
                        System.out.println("The sleep has decreased by 25 now is: " + sleep);
                    } else {
                        sleep = sleep - 20;
                        System.out.println("The sleep has decreased by 20 now is: " + sleep);
                    }
                    break;
                case 2:
                    fatigue = fatigue + 15;
                    System.out.println("The fatigue has increased by 20 now is: " + fatigue);
                    energy = energy - 10;
                    System.out.println("The energy has decreased by 25 now is: " + energy);
                    hygiene = hygiene - 15;
                    System.out.println("The hygiene has decreased by 15 now is: " + hygiene);
                    weight = weight - 10;
                    System.out.println("The weight has decreased by 10 now is: " + weight);
                    if (dogcat == 1) {
                        sleep = sleep - 10;
                        System.out.println("The sleep has decreased by 10 now is: " + sleep);
                    } else {
                        sleep = sleep + 15;
                        System.out.println("The sleep has increased by 15 now is: " + sleep);
                    }
                    break;
                case 3:
                    energy = energy + 10;
                    System.out.println("The energy has increased by 10 now is: " + energy);
                    hygiene = hygiene - 10;
                    System.out.println("The hygiene has decreased by 10 now is: " + hygiene);
                    weight = weight + 10;
                    System.out.println("The weight has increased by 10 now is: " + weight);
                    if (dogcat == 1) {
                        sleep = sleep + 15;
                        System.out.println("The sleep has increased by 15 now is: " + sleep);
                    } else {
                        sleep = sleep + 10;
                        System.out.println("The sleep has increased by 10 now is: " + sleep);
                    }
                    break;
                case 4:
                    if (dogcat == 1) {
                        sleep = sleep - 15;
                        System.out.println("The sleep has decreased by 15 now is: " + sleep);
                        hygiene = hygiene + 20;
                        System.out.println("The hygiene has increased by 20 now is: " + hygiene);
                    } else {
                        sleep = sleep + 10;
                        System.out.println("The sleep has increased by 10 now is: " + sleep);
                        hygiene = hygiene + 20;
                        System.out.println("The hygiene has increased by 20 now is: " + hygiene);
                    }
                    break;
                default:
                    System.out.println("This value is incorrect.");
            }
            if (energy <= 0 || energy >= 100 || fatigue <= 0 || fatigue >= 100 || sleep <= 0 || sleep >= 100 || hygiene <= 0 || hygiene >= 100 || weight <= 0 || weight >= 100) {
                dead = true;
                exit = true;
            }
        } while (!exit);
        if (dead){
            System.out.println(" ");
            System.out.println(name + " died because you took bad care of him.");
        }
        if (menu == 0) {
            System.out.println(" ");
            System.out.println("You are abandoning your pet " + name + ". :(");
            System.out.println("The stats of your pet were: Energy: " + energy + ", Fatigue: " + fatigue + ", Sleep: " + sleep + ", Hygiene: " + hygiene + " and Weight: " + weight);
        }
    }
}