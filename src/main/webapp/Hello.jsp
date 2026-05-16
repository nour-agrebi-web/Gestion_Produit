<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion — Gestion Produits</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">

        <div class="auth-header">
            <div class="auth-logo">🛍️</div>
            <h1 class="auth-title">Bienvenue</h1>
            <p class="auth-subtitle">Gestion des Produits <br> Connectez-vous</p>
        </div>

        <div class="auth-body">

            <%-- Messages d'erreur --%>
            <% String error = (String) request.getAttribute("error");
               if (error != null) { %>
                <div class="alert alert-error">⚠️ <%= error %></div>
            <% } %>
            <%-- Message de succès inscription --%>
            <% String success = (String) request.getAttribute("success");
               if (success != null) { %>
                <div class="alert alert-success"> <%= success %></div>
            <% } %>

            <form action="hello" method="POST">

                <div class="form-group">
                    <label class="form-label">Login</label>
                    <input type="text" name="login" class="form-input"
                           placeholder="Votre identifiant" required autofocus>
                </div>

                <div class="form-group">
                    <label class="form-label">Mot de passe</label>
                    <input type="password" name="password" class="form-input"
                           placeholder="••••••••" required>
                </div>

                <button type="submit" class="btn btn-primary" style="width:100%;justify-content:center;margin-top:.5rem;">
                    Se connecter 
                </button>

            </form>

            <div class="auth-footer" style="margin-top:1.5rem;">
                Pas encore de compte ?
                <a href="register">S'inscrire</a>
            </div>

        </div>
    </div>
</div>
</body>
</html>
