package pl.coderslab.model;


import java.util.Scanner;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int idUser;
    private int idExercise;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }


    public Solution() {
    }

    public Solution(int idUser, int idExercise) {
        this.idUser = idUser;
        this.idExercise = idExercise;
    }

    public Solution(int idUser, int idExercise, String description) {
        this.idUser = idUser;
        this.idExercise = idExercise;
        this.description = description;
    }

    public Solution(String created, String updated, String description) {
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(int id, String created, String updated, String description) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void chooseOperationSolution() {

        System.out.println("Wpisz ADD jeśli chcesz stworzyć nowe rozwiązanie,");
        System.out.println("Wpisz VIEW jeśli chcesz pobrać istniejące rowiązania konkretnego użytkownika,");
        System.out.println("Wpisz EDIT jeśli chcesz zaktualizować dane istniejącego rozwiązania,");
        System.out.println("Wpisz DELETE jeśli chcesz usunąć istniejące rozwiązanie.");
        System.out.println("Wpisz QUIT jeśli chcesz zakończyć program");

        Scanner scan = new Scanner(System.in);
        String operation = scan.nextLine();
        operation = operation.toLowerCase();
        if (operation.equals("add")) {
            create();
        }
        else if (operation.equals("view")) {
            findAllByUserId();

        }
        else if (operation.equals("edit")) {
            update();
        }
        else if (operation.equals("delete")) {
            delete();
        }
        else if (operation.equals("find")) {
            findAll();
        }
        else if (operation.equals("quit")) {

        }

    }

    static void create() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Stwórz created (yyyy-mm-dd 00:00:00):");
        String created = scan.nextLine();
        System.out.println("Stwórz updated (yyyy-mm-dd 00:00:00):");
        String updated = scan.nextLine();
        System.out.println("Stwórz description:");
        String description = scan.nextLine();

        Solution solution = new Solution(created, updated, description);
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);

    }

    static Solution read() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj id rozwiązania, które chcesz wyświetlić");
        String id = "";
        int id2 = -1;
        while (id.equals("")) {

            id = scan.nextLine();
            try {
                id2 = Integer.parseInt(id);
            } catch (NumberFormatException e) {

            }
        }

        SolutionDao solutionDao = new SolutionDao();

        return solutionDao.read(id2);
    }

    public static Solution[] findAllByUserId() {
        SolutionDao solutionDao = new SolutionDao();
        Scanner scan = new Scanner(System.in);
        String id = "";
        int id2 = -1;
        while (id.equals("")) {
            id = scan.nextLine();
            try {
                id2 = Integer.parseInt(id);
            } catch (NumberFormatException e) {

            }
        }
        for (int i = 0; i < solutionDao.findAllByUserId(id2).length; i++) {
            System.out.println(toString(solutionDao.findAllByUserId(id2)[i]));
        }


        return solutionDao.findAll();
    }

    static void update() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj id rozwiązania");
        String id = "";
        int id2 = -1;
        while (id.equals("")) {
            id = scan.nextLine();
            try {
                id2 = Integer.parseInt(id);
            } catch (NumberFormatException e) {

            }
        }
        System.out.println("Podaj created (yyyy-mm-dd 00:00:00):");
        String created = scan.nextLine();
        System.out.println("Podaj updated (yyyy-mm-dd 00:00:00):");
        String updated = scan.nextLine();
        System.out.println("Podaj description:");
        String description = scan.nextLine();

        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.read(id2);
        solution.setCreated(created);
        solution.setUpdated(updated);
        solution.setDescription(description);
        solutionDao.update(solution);
    }

    static void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj id rozwiązania");
        String id = "";
        int id2 = -1;
        while (id.equals("")) {
            id = scan.nextLine();
            try {
                id2 = Integer.parseInt(id);
            } catch (NumberFormatException e) {

            }
        }
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.delete(id2);

    }

    static String toString(Solution solution) {
        int id = solution.getId();
        String created = solution.getCreated();
        String updated = solution.getUpdated();
        String description = solution.getDescription();
        return id + " " + created + " " + updated + " " + description;
    }


    public static Solution[] findAll() {
        SolutionDao solutionDao = new SolutionDao();
        for (int i = 0; i < solutionDao.findAll().length; i++) {
            System.out.println(toString(solutionDao.findAll()[i]));
        }


        return solutionDao.findAll();
    }

}
