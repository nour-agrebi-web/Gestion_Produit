<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription — Gestion Produits</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">

        <div class="auth-header">
            <div class="auth-logo">✨</div>
            <h1 class="auth-title">Inscription</h1>
            <p class="auth-subtitle">Créez votre compte utilisateur</p>
        </div>

        <div class="auth-body">

            <% String error = (String) request.getAttribute("error");
               if (error != null) { %>
                <div class="alert alert-error">⚠️ <%= error %></div>
            <% } %>

            <form action="register" method="POST">

                <div class="form-group">
                    <label class="form-label">Login</label>
                    <input type="text" name="login" class="form-input"
                           placeholder="Choisissez un identifiant" required autofocus>
                </div>

                <div class="form-group">
                    <label class="form-label">Mot de passe</label>
                    <input type="password" name="password" class="form-input"
                           placeholder="Minimum 4 caractères" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Confirmer le mot de passe</label>
                    <input type="password" name="confirm" class="form-input"
                           placeholder="Répétez le mot de passe" required>
                </div>

                <button type="submit" class="btn btn-success" style="width:100%;justify-content:center;margin-top:.5rem;">
                    Créer mon compte →
                </button>

            </form>

            <div class="auth-footer" style="margin-top:1.5rem;">
                Déjà inscrit ?
                <a href="hello">Se connecter</a>
            </div>

        </div>
    </div>
</div>
</body>
</html>
