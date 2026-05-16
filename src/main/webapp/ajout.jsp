<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) { response.sendRedirect("Hello.jsp"); return; }
    String role = (String) session.getAttribute("role");
    if (!"admin".equals(role)) { response.sendRedirect("home.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un produit</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<nav class="navbar">
    <a href="home.jsp" class="navbar-brand"><span class="dot"></span> Gestion Produits</a>
    <div class="navbar-info">
        <span class="navbar-user">👤 <%= user %></span>
        <span class="badge-role admin">Administrateur</span>
        <a href="logout" class="btn-logout">Déconnexion</a>
    </div>
</nav>

<div class="page-wrapper">
    <h1 class="page-title">Nouveau Produit</h1>
    <p class="page-subtitle">Remplissez le formulaire pour ajouter un produit</p>

    <div class="card" style="max-width:520px;">
        <form action="add" method="POST">

            <div class="form-group">
                <label class="form-label">Nom du produit</label>
                <input type="text" name="nom" class="form-input"
                       placeholder="Ex: iPhone 15 Pro" required autofocus>
            </div>

            <div class="form-group">
                <label class="form-label">Prix (DT)</label>
                <input type="text" name="prix" class="form-input"
                       placeholder="Ex: 2800" step="0.01" min="0" required>
            </div>

            <div style="display:flex;gap:1rem;margin-top:1.5rem;">
                <button type="submit" class="btn btn-success"> Ajouter le produit</button>
                <a href="home.jsp" class="btn btn-outline">Retour</a>
            </div>

        </form>
    </div>
</div>
</body>
</html>
