/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseasesearchcbr;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Đảm
 */
public class DiseaseSearchQuery extends HttpServlet{

    private static final long serialVersionUID = 4564048408781536527L;

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
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Các hệ thống dựa trên tri thức</title>");
            out.println("<link href='style.css' rel='stylesheet' type='text/css' />");
            out.println("</head>");
            out.println("<body>");
            out.println(
                    "<div id='nav'><h1><span class='page-title'>Trả lời các câu hỏi sau để hệ thống phán đoán về tình trạng sức khoẻ</span></h1></div>");
            out.println(""
                    + "    <form method='POST' action='DiseaseSearchResults' name='queryForm'>\n"
                    + "        <div class='query'>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='face'>Trên toàn bộ khuôn mặt người bệnh có biểu hiện như thế nào?</label>\n"
                    + "                <input type='radio' name='face' value='FAC1' checked='checked'> Nhăn nhó\n"
                    + "                <input type='radio' name='face' value='FAC2'> Co giật nửa mặt\n"
                    + "                <input type='radio' name='face' value='FAC3'> Co giật\n"
                    + "                <input type='radio' name='face' value='FAC4'> Liệt nửa mặt\n"
                    + "                <input type='radio' name='face' value='FAC5'> Liệt cả mặt\n"
                    + "                <input type='radio' name='face' value='FAC6'> Đau nửa mặt\n"
                    + "                <input type='radio' name='face' value='FAC7'> Bình thường\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='eyes'>Mắt của người bệnh có những biểu hiện như thế nào?</label>\n"
                    + "                <input type='radio' name='eyes' value='EYE1' checked='checked'> Chớp mắt nhanh\n"
                    + "                <input type='radio' name='eyes' value='EYE2'> Co giật\n"
                    + "                <input type='radio' name='eyes' value='EYE3'> Khô mắt\n"
                    + "                <input type='radio' name='eyes' value='EYE4'> Mí mắt xệ xuống\n"
                    + "                <input type='radio' name='eyes' value='EYE5'> Chảy nước mắt quá nhiều ở một bên\n"
                    + "                <input type='radio' name='eyes' value='EYE6'> Mất thị lực\n"
                    + "                <input type='radio' name='eyes' value='EYE7'> Bình thường\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='mouth'>Miệng của người bệnh có những biểu hiện như thế nào?</label>\n"
                    + "                <input type='radio' name='mouth' value='MOU1' checked='checked'> Môi mím lại\n"
                    + "                <input type='radio' name='mouth' value='MOU2'> Co giật\n"
                    + "                <input type='radio' name='mouth' value='MOU3'> Trề môi\n"
                    + "                <input type='radio' name='mouth' value='MOU4'> Giật khoé miệng\n"
                    + "                <input type='radio' name='mouth' value='MOU5'> Đau\n"
                    + "                <input type='radio' name='mouth' value='MOU6'> Méo miệng\n"
                    + "                <input type='radio' name='mouth' value='MOU7'> Nhỏ dãi\n"
                    + "                <input type='radio' name='mouth' value='MOU8'> Bình thường\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='tongue'>Lưỡi của người bệnh có những biểu hiện như thế nào?</label>\n"
                    + "                <input type='radio' name='tongue' value='TON1' checked='checked'> Lè lưỡi\n"
                    + "                <input type='radio' name='tongue' value='TON2'> Mất vị giác\n"
                    + "                <input type='radio' name='tongue' value='TON3'> Đau\n"
                    + "                <input type='radio' name='tongue' value='TON4'> Cơ lưỡi tự di chuyển\n"
                    + "                <input type='radio' name='tongue' value='TON5'> Bình thường\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='jaw'>Phần hàm của người bệnh có những biểu hiện như thếnào?</label>\n"
                    + "                <input type='radio' name='jaw' value='JAW1' checked='checked'> Co giật\n"
                    + "                <input type='radio' name='jaw' value='JAW2'> Lệch hàm\n"
                    + "                <input type='radio' name='jaw' value='JAW3'> Đau\n"
                    + "                <input type='radio' name='jaw' value='JAW4'> Nhai đi nhai lại\n"
                    + "                <input type='radio' name='jaw' value='JAW5'> Bình thường\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='age'>Người bệnh đang ở độ tuổi nào?</label>\n"
                    + "                <input type='radio' name='age' value='AGE1' checked='checked'> Từ 2 đến 15 tuổi\n"
                    + "                <input type='radio' name='age' value='AGE2'> Từ 15 đến 40 tuổi\n"
                    + "                <input type='radio' name='age' value='AGE3'> Từ 40 đến 60 tuổi\n"
                    + "                <input type='radio' name='age' value='AGE4'> Trên 60 tuổi\n"
                    + "                <input type='radio' name='age' value='AGE5'> All\n"
                    + "            </div>\n"
                    + "            <div class='inputfield'>\n"
                    + "                <label for='speak'>Người bệnh đang ở độ tuổi nào?</label>\n"
                    + "                <input type='radio' name='speak' value='SPE1' checked='checked'> Khó nói\n"
                    + "                <input type='radio' name='speak' value='SPE2'> Lặp đi lặp lại các từ\n"
                    + "                <input type='radio' name='speak' value='SPE3'> Bình thường\n"
                    + "            </div>\n"
                    + "            </fieldset>\n"
                    + "            <fieldset id='imaging'>\n"
                    + "                <button type='submit' id='send'>Search</button>\n"
                    + "                <button type='reset' id='reset'>Reset</button>\n"
                    + "        </div>\n"
                    + "    </form>");
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
