<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur — Gestion Produits</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">
        <div class="auth-header" style="background:linear-gradient(135deg,#b03a3a,var(--rouge,#CE6A6B));">
            <div class="auth-logo" style="background:rgba(255,255,255,.2);">⚠️</div>
            <h1 class="auth-title">Accès refusé</h1>
            <p class="auth-subtitle">Une erreur s'est produite</p>
        </div>
        <div class="auth-body" style="text-align:center;">
            <% String error = (String) request.getAttribute("error");
               if (error != null) { %>
            <div class="alert alert-error" style="justify-content:center;">
                <%= error %>
            </div>
            <% } else { %>
            <div class="alert alert-error" style="justify-content:center;">
                Login ou mot de passe incorrect.
            </div>
            <% } %>
            <a href="Hello.jsp" class="btn btn-primary" style="margin-top:1rem;">
                 Retour à la connexion
            </a>
        </div>
    </div>
</div>
</body>
</html>
