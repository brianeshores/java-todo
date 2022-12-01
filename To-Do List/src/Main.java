import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Press <Enter> to Begin...");
        Scanner input = new Scanner(System.in);
        input.nextLine();

        boolean isRunning = true;
        int option;

        while(isRunning) {
            System.out.println("------------------------------------------------------------------------------------------------------------------- \n");
            System.out.println("Choose an Option:");
            System.out.println("(1) Add a task");
            System.out.println("(2) Mark the task complete");
            System.out.println("(3) Remove the task");
            System.out.println("(4) Edit task");
            System.out.println("(5) Display all tasks");
            System.out.println("(6) Exit \n");
            while(true) {
                try {
                    Scanner input2 = new Scanner(System.in);
                    System.out.print("What would you like to do(1-6): ");
                    option = input2.nextInt();
                    input2.nextLine();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.print("Please try a number between 1-6");
                }
            }
            switch (option) {
                case 1 -> {
                    Task task = new Task();
                    System.out.print("Enter Title: ");
                    task.setTitle(input.nextLine());
                    System.out.print("Enter due date (dd/mm/yyyy): ");
                    while(true) {
                        try {
                            String inputDate = input.nextLine();
                            task.setDate(sdf.parse(inputDate));
                            break;
                        } catch (ParseException e) {
                            System.out.print("Please enter a date in the format (dd/mm/yyyy)");
                            System.out.println();
                        }
                    }
                    task.setStatus("Incomplete");
                    System.out.print("Add a description: ");
                    task.setDescription(input.nextLine());
                    taskList.add(task);
                    System.out.println("\n\n");
                }
                case 2 -> {
                    while (true) {
                       try {
                           System.out.print("Which task # is complete? (0 for more options): ");
                           Scanner input3 = new Scanner(System.in);
                           option = input3.nextInt();
                           input3.nextLine();
                           if(option > taskList.size()) {
                               System.out.println("Task does not exist. Please try another task #");
                           } else {
                               break;
                           }
                       }
                       catch (InputMismatchException e){
                           System.out.print("Please try a number: ");
                        }
                    }
                    if (option != 0) {
                        taskList.get(option - 1).setStatus("Complete");
                        System.out.println("Task #" + option + " set to Complete.");
                    }
                }
                case 3 -> {
                    while (true) {
                        try {
                            System.out.print("Which task # would you like to remove? (0 for more options): ");
                            Scanner input4 = new Scanner(System.in);
                            option = input4.nextInt();
                            if (option > taskList.size()) {
                                System.out.print("Task does not exist. Please try another task #. (0 - for more options): ");
                            }
                            else {
                                break;
                            }
                        }
                        catch(InputMismatchException e) {
                            System.out.print("Please try a number: ");
                        }
                    }
                    if (option != 0) {
                        taskList.remove(option - 1);
                        System.out.println("Task #" + option + " removed.");
                    }
                }
                case 4 -> {
                    while(true) {
                        try {
                            System.out.print("Which task # would you like to edit?: ");
                            Scanner input5 = new Scanner(System.in);
                            option = input5.nextInt();
                            if (option > taskList.size()) {
                                System.out.print("Task does not exist. Please try another task #. (0 - for more options): ");
                            } else {
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("Please try a number:");
                        }
                    }
                    if (option != 0) {
                        System.out.print("Enter Title: ");
                        taskList.get(option-1).setTitle(input.nextLine());
                        System.out.print("Enter due date (dd/mm/yyyy): ");
                        while(true) {
                            try {
                                taskList.get(option-1).setDate(sdf.parse(input.next()));
                                break;
                            } catch (ParseException e) {
                                System.out.print("Please enter a date in the format (dd/mm/yyyy)");
                            }
                        }
                        System.out.print("Status (Complete/Incomplete: ");
                        taskList.get(option-1).setStatus(input.nextLine());
                        System.out.print("Add a description: ");
                        taskList.get(option-1).setDescription(input.nextLine());
                        System.out.println("\n Task#" + option + " has been edited");
                    }
                }
                case 5 -> {
                    System.out.println("Title                     Due Date            Status                  Description");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------");
                    for (Task task : taskList) {
                        System.out.printf("%-25s", task.getTitle());
                        System.out.printf("%-19s", sdf.format(task.getDate()));
                        System.out.printf("%-25s", task.getStatus());
                        System.out.printf("%-15s", task.getDescription());
                        System.out.println();
                    }
                    System.out.println("\n");
                }
                case 6 -> isRunning = false;
            }
        }
    }
}