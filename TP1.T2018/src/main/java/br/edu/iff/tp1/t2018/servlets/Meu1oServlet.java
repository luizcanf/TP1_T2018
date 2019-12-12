/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iff.tp1.t2018.servlets;

import br.edu.iff.tp1.t2018.entidades.Endereco;
import br.edu.iff.tp1.t2018.entidades.Usuario;
import br.edu.iff.tp1.t2018.utilidades.HibernateUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author samsung
 */
public class Meu1oServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String nomeCompleto = nome + " " + sobrenome;
        System.out.println("Nome completo: " + nomeCompleto);
        String senha = request.getParameter("senha");

        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String cpf2 = request.getParameter("cpf2");
        
        Usuario user = new Usuario();
        user.setNome(nomeCompleto);
        user.setSenha(senha);

        user.setDocRg(rg);
        if (cpf != null && cpf2 != null) {
            user.setDocCpf(Long.parseLong(cpf+cpf2));
        }

        Random rng = new Random();
        user.setIdUsuario(rng.nextInt(Integer.MAX_VALUE));
        
        Endereco endResidencial = new Endereco();
        endResidencial.setIdEndereco(rng.nextInt(Integer.MAX_VALUE));
        
        String rua = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String bairro = request.getParameter("bairro");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String cep1 = request.getParameter("cep");
        String cep2 = request.getParameter("cep2");
        
        endResidencial.setRua(rua);
        endResidencial.setNumero(numero);
        endResidencial.setBairro(bairro);
        endResidencial.setEstado(estado);
        endResidencial.setMunicipio(cidade);
        if (cep1 != null && cep2 != null) {
            endResidencial.setCep(Integer.parseInt(cep1+cep2));
        }
        
        endResidencial.setUsuaIdUsuario(user);
        user.getEnderecoCollection().add(endResidencial);

        Session sessaoBD = HibernateUtil.getSession();
        Transaction tr = sessaoBD.beginTransaction();
        sessaoBD.save(user);
        sessaoBD.save(endResidencial);
        tr.commit();
        sessaoBD.close();

        response.sendRedirect("testezinho.jsp");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    }// </editor-fold>

}
