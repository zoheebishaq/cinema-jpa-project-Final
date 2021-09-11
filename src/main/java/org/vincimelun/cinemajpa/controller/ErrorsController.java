package org.vincimelun.cinemajpa.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        // Code d'erreur par défaut = 500
        int code = 500;
        // Message d'erreur par défaut = ""
        String raison = "";

        // Récupération du code et du message d'erreur
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object statusMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        // Modification du code par défaut par celui récupéré si il existe
        if (statusCode != null) {
            code = Integer.parseInt(statusCode.toString());
        }
        // Modification du message par défaut par celui récupéré si il existe
        if (statusMessage != null) {
            raison = statusMessage.toString();
        }

        // Ajout de l'attribut code à la vue pour valeur : code d'erreur
        model.addAttribute("code", code);
        // Ajout de l'attribut raison à la vue pour valeur : message de la raison de l'erreur
        model.addAttribute("raison", raison);

        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}