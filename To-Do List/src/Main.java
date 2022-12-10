import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void createTask(ArrayList<Task> taskList, Scanner input, SimpleDateFormat sdf) {
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

    public static void setComplete(ArrayList<Task> taskList, Scanner input) {
        int option;
        while (true) {
            try {
                System.out.print("Which task # is complete? (0 for more options): ");

                option = input.nextInt();
                input.nextLine();
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

    public static void removeTask(ArrayList<Task> taskList, Scanner input) {
        int option;
        while (true) {
            try {
                System.out.print("Which task # would you like to remove? (0 for more options): ");
                option = input.nextInt();
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

    public static void editTask(ArrayList<Task> taskList, Scanner input, SimpleDateFormat sdf) {
        int option;
        while(true) {
            try {
                System.out.print("Which task # would you like to edit?: ");
                option = input.nextInt();
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
            input.nextLine();
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
            input.nextLine();
            System.out.print("Status (Complete/Incomplete: ");
            taskList.get(option-1).setStatus(input.nextLine());
            System.out.print("Add a description: ");
            taskList.get(option-1).setDescription(input.nextLine());
            System.out.println("\n Task#" + option + " has been edited");
        }
    }

    public static void printTasks(ArrayList<Task> taskList, SimpleDateFormat sdf) {
        System.out.printf("%-40s| %-11s| %-11s| %-40s", "TaskName", "Due Date", "Status", "Description");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf(String.format("%-40s| %-11s| %-11s| %-40s", i + 1 + ". " + taskList.get(i).getTitle(), sdf.format(taskList.get(i).getDate()),
                    taskList.get(i).getStatus(), taskList.get(i).getDescription()));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Task> taskList = new ArrayList<>();

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
                    System.out.print("Please try a number between 1-6 \n");
                }
            }
            switch (option) {
                case 1 -> createTask(taskList, input, sdf);
                case 2 -> setComplete(taskList, input);
                case 3 -> removeTask(taskList, input);
                case 4 -> editTask(taskList, input, sdf);
                case 5 -> printTasks(taskList, sdf);
                case 6 -> isRunning = false;
            }
        }
    }
}