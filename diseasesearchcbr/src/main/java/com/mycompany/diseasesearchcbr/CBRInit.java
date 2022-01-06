/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseasesearchcbr;

import de.dfki.mycbr.core.Project;

/**
 *
 * @author Quang Đảm
 */
public class CBRInit {

    private final String data_path = "D:\\Documents\\NetBeansProjects\\diseasesearchcbr\\src\\main\\webapp\\WEB-INF\\classes\\";
    private static String projectName = "CaseBaseBenh.prj";
    private static String conceptName = "Benh";
    private static String casebase = "cases-benh";

    String getCaseBase() {
        return casebase;
    }

    public static void setCasebase(String casebase) {
        CBRInit.casebase = casebase;
    }

    public static String getProjectName() {
        return projectName;
    }

    public static void setProjectName(String projectName) {
        CBRInit.projectName = projectName;
    }

    static String getConceptName() {
        return conceptName;
    }

    public static void setConceptName(String conceptName) {
        CBRInit.conceptName = conceptName;
    }

    /**
     * This methods loads a myCBR project from a .prj file and loads the cases
     * in this project. The specification of the project's location and
     * according file names has to be done at the beginning of this class.
     *
     * @return Project instance containing model, sims and cases (if available)
     */
    Project createProjectFromPRJ() {

        Project project = null;

        try {
            System.out.println("data path: " + data_path);
            // load new project
            project = new Project(data_path + projectName);
            // create a concept and get the main concept of the project;
            // the name has to be specified at the beginning of this class
            while (project.isImporting()) {
                Thread.sleep(1000);
                System.out.print(". ");
            }
            System.out.println("\nProject imported successfully.");
        } catch (Exception ex) {
            System.out.println("Please move your Project files to " + data_path);
        }

        return project;
    }

    /**
     * This methods creates an EMPTY myCBR project. The specification of the
     * project's location and according file names has to be done at the
     * beginning of this class.
     *
     * @return Project instance containing model, sims and cases (if available)
     */
    public Project createemptyCBRProject() {

        Project project = null;

        try {
            // load new project
            project = new Project(data_path + projectName);
            // create a concept and get the main concept of the project;
            // the name has to be specified at the beginning of this class
            while (project.isImporting()) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            System.out.print("\n"); // console pretty print
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return project;
    }
}
