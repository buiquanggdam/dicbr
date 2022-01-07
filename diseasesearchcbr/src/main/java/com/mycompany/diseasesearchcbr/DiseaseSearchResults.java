/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseasesearchcbr;

import de.dfki.mycbr.core.ICaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Đảm
 */
public class DiseaseSearchResults extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Project prj;
    private final ICaseBase defaultCB;
    private final Concept concept;
    private int casesCount = 2;

    public DiseaseSearchResults() {
        CBRInit cbrinit = new CBRInit();
        prj = cbrinit.createProjectFromPRJ();
        defaultCB = prj.getCB(cbrinit.getCaseBase());
        concept = prj.getConceptByID(CBRInit.getConceptName());
    }

    private ArrayList<Hashtable<String, String>> parseCSQuery(String face1, String eyes1, String mouth1, String tongue1, String jaw1, String age1, String speak1) {

        int totalCasesCount = defaultCB.getCases().size();

        // Maximale Anzahl der gefundenen Fälle auf die Gesamtanzahl der Fälle
        // in der Fallbasis reduzieren, falls sie größer ist als die Gesamtanzahl
        if (casesCount > totalCasesCount) {
            casesCount = totalCasesCount;
        }

        SymbolDesc face = (SymbolDesc) concept.getAllAttributeDescs().get("Face");
        SymbolDesc eye = (SymbolDesc) concept.getAllAttributeDescs().get("Eyes");
        SymbolDesc mouth = (SymbolDesc) concept.getAllAttributeDescs().get("Mouth");
        SymbolDesc tongue = (SymbolDesc) concept.getAllAttributeDescs().get("Tongue");
        SymbolDesc jaw = (SymbolDesc) concept.getAllAttributeDescs().get("Jaw");
        SymbolDesc age = (SymbolDesc) concept.getAllAttributeDescs().get("Age");
        SymbolDesc speak = (SymbolDesc) concept.getAllAttributeDescs().get("Speak");

        // Retrieval initialisieren und Methode (sortiert absteigend nach Ähnlichkeit) festlegen
        Retrieval disRetrieval = new Retrieval(concept, defaultCB);
        disRetrieval.setRetrievalMethod(Retrieval.RetrievalMethod.RETRIEVE_SORTED);

        Instance queryInstance = disRetrieval.getQueryInstance();

        try {
            queryInstance.addAttribute(face, face.getAttribute(face1));
            queryInstance.addAttribute(eye, eye.getAttribute(eyes1));
            queryInstance.addAttribute(mouth, mouth.getAttribute(mouth1));
            queryInstance.addAttribute(tongue, tongue.getAttribute(tongue1));
            queryInstance.addAttribute(jaw, jaw.getAttribute(jaw1));
            queryInstance.addAttribute(age, age.getAttribute(age1));
            queryInstance.addAttribute(speak, speak.getAttribute(speak1));
        } catch (Exception ex) {
            Logger.getLogger(DiseaseSearchResults.class.getName()).log(Level.SEVERE, null, ex);
        }

//        camRetrieval.start();
        disRetrieval.start();
        // Get the results
        List<Pair<Instance, Similarity>> resultList = disRetrieval.getResult();
//        String disease = null;
//        
//        queryInstance.addAttribute(disease, queryInstance);

        defaultCB.addCase(queryInstance);

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i).toString());
        }
        ArrayList<Hashtable<String, String>> resultTable;
        if (resultList.size() > 0) {
            resultTable = new ArrayList<>();
            for (int i = 0; i < casesCount; i++) {
                resultTable.add(getAttributes(resultList.get(i), prj.getConceptByID(CBRInit.getConceptName())));
                System.out.println("liste " + resultTable.get(i).toString());
            }
        } else {
            resultTable = null;
        }

        return resultTable;
    }

    /**
     * This method delivers a Hashtable which contains the Attributs names
     * (Attributes of the case) combined with their respective values.
     *
     * @param r = An Instance.
     * @param concept = A Concept
     * @return List = List containing the Attributes of a case with their
     * values.
     */
    private static Hashtable<String, String> getAttributes(Pair<Instance, Similarity> r, Concept concept) {

        Hashtable<String, String> resTable = new Hashtable<>();
        ArrayList<String> categories = getCategories(r);
        // Add the similarity of the case
        resTable.put("Sim", String.valueOf(r.getSecond().getValue()));
        categories.forEach(category -> {
            // Add the Attribute name and its value into the Hashtable
            resTable.put(category,
                    r.getFirst().getAttForDesc(concept.getAllAttributeDescs().get(category))
                            .getValueAsString());
        });
        return resTable;
    }

    /**
     * This Method generates an ArrayList, which contains all Categories of aa
     * Concept.
     *
     * @param r = An Instance.
     * @return List = List containing the Attributes names.
     */
    private static ArrayList<String> getCategories(Pair<Instance, Similarity> r) {

        ArrayList<String> categories = new ArrayList<>();
        // Read all Attributes of a Concept
        Set<AttributeDesc> categoryList = r.getFirst().getAttributes().keySet();
        for (AttributeDesc category : categoryList) {
            if (category != null) {
                // Add the String literals for each Attribute into the ArrayList
                categories.add(category.getName());
            }
        }
        return categories;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Hashtable<String, String>> queryResult = parseCSQuery(
                request.getParameter("face"),
                request.getParameter("eyes"),
                request.getParameter("mouth"),
                request.getParameter("tongue"),
                request.getParameter("jaw"),
                request.getParameter("age"),
                request.getParameter("speak"));

        // Ergebnisse ausgeben
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Kết quả chẩn đoán</title>");
            out.println("<link href='style.css' rel='stylesheet' type='text/css' />");
            out.println("<link href='iconfont.css' rel='stylesheet' type='text/css' />");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<div id='nav'><p>Chẩn đoán bệnh <span class='page-title'>/ Kết quả</span></p></div>");

            out.println("<h3 id='new-query'><a href='DiseaseSearchQuery'>Thử lại</a></h3>");

            out.println("<div id='search-results'>");
            queryResult.stream().map((queryRes) -> {
                Hashtable<String, String> singleResult;
                singleResult = queryRes;
                return singleResult;
            }).forEach((singleResult) -> {

                // Đầu ra của các thuộc tính riêng lẻ của kết quả trong một bảng
                out.println("<div class = 'result'>"
                        + "Người bệnh bị: " + singleResult.get("Disease")+ "</div>");
                
                Enumeration<String> items = singleResult.keys();

                // Đầu ra của các nút và giá trị tương tự
                out.println("<div><div>" + "SimScore: <strong>" + singleResult.get("Sim")
                        + "</strong></div>" + "</div>");
            });
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
