import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManagerCommands {
    private Scanner sc = new Scanner(System.in);
    private String command = sc.nextLine();
    private String pathName = "C:\\Users\\yoshi\\Desktop\\123.txt";
    File file = new File(pathName);


    public void acts(String command) throws IOException {
        if (command.equals("create")){
            createTask();
        }
        else if(command.equals("edit")) {
            editTask(this.file);
        }
        else if(command.equals("delete")) {
            System.out.println("Введите имя таска, который хотите удалить");
            Scanner scanInput = new Scanner(System.in);
            String taskNameInput = scanInput.nextLine();
            deleteTask(this.file, taskNameInput);
        }
        else if(command.equals("show task")) {
            System.out.println("Введите имя таска, который хотите просмотреть");
            Scanner scanInput = new Scanner(System.in);
            String taskNameInput = scanInput.nextLine();
            showTask(this.file, taskNameInput);
        }
        else{
            System.out.println("Введена некорректная команда, повторите ввод");
            Scanner scan = new Scanner(System.in);
            String newCommand = scan.nextLine();
            this.command = newCommand;
        }
    }

    public void createTask() throws IOException {

        FileWriter writer = new FileWriter(file, true);
        Task task = new Task();
        System.out.println("Введите имя таска");
        Scanner scanner = new Scanner(System.in);
        String newTaskName = scanner.nextLine();
        task.setName(newTaskName);
        writer.write(task.getName() + "\n");
        System.out.println("Введите содержание таска");
        String newTaskDescription = scanner.nextLine();
        task.setDescription(newTaskDescription);
        writer.write(task.getDescription() + "\n");
        writer.close();
    }
    //
    public void editTask(File file) throws IOException {
        Scanner scanFile = new Scanner(file);
        Scanner scanInput = new Scanner(System.in);
        System.out.println("Введите имя редактируемого таска");
        String taskNameInput = scanInput.nextLine();
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        task.setNewTaskData(oldTaskDesc, taskNameInput, task);
        task.putNewTaskDataInFile(file, taskNameInput, oldTaskDesc, task);
    }


    public void deleteTask(File file, String taskNameInput) throws IOException {
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        task.deleteTaskFromFile(file, taskNameInput, oldTaskDesc);
        System.out.println("Таск " + taskNameInput + " удален");
    }

    public void showTask(File file, String taskNameInput) throws FileNotFoundException {
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        if(oldTaskDesc.equals("null")){
            System.out.println("Таск с введенным именем не найден");
        }
        else {
            System.out.println(taskNameInput + "\n" + oldTaskDesc);
        }
    }
}