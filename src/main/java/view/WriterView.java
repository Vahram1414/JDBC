//package view;
//
//import controller.WriterController;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class WriterView {
//    private final WriterController writerController = new WriterController();
//    private final Scanner scannerId = new Scanner(System.in);
//    private final Scanner firstName = new Scanner(System.in);
//    private final Scanner lastName = new Scanner(System.in);
//
//    public void create() {
//        System.out.println("Enter firstName writer");
//        String first = firstName.nextLine();
//        System.out.println("Enter lastName writer");
//        String last = lastName.nextLine();
//        WriterView writer = writerController.create(first, last);
//    }
//
//    public void getAll() {
//        List <WriterView> writerList = writerController.writerList();
//    }
//
//    public void update() {
//        System.out.println("Enter id writer");
//        Integer id = scannerId.nextInt();
//        System.out.println("Enter first name writer");
//        String first = firstName.nextLine();
//        System.out.println("Enter last name writer");
//        String last = lastName.nextLine();
//        WriterView writer = WriterController.update(id, first, last);
//        System.out.println("You have entered " + writer);
//    }
//
//    public void delete() {
//        System.out.println("Enter id writer");
//        Integer id = scannerId.nextInt();
//        writerController.delete(id);
//    }
//
//    public void getById() {
//        System.out.println("Enter id writer");
//        Integer id = scannerId.nextInt();
//        WriterView writer = writerController.getById(id);
//        System.out.println(writer.toString());
//    }
//}
