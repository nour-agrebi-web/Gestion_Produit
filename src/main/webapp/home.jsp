<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.produits" %>
<%@ page import="java.util.List" %>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) { response.sendRedirect("Hello.jsp"); return; }
    String role = (String) session.getAttribute("role");
    boolean isAdmin = "admin".equals(role);
    boolean isUser = "user".equals(role);
    List<produits> prod = (List<produits>) session.getAttribute("produits");
    int total = (prod != null) ? prod.size() : 0;
    double totalPrix = 0;
    if (prod != null) for (produits p : prod) totalPrix += p.getPrix();
    
    
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil — Gestion Produits</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar">
    <a href="home.jsp" class="navbar-brand">
        <span class="dot"></span> Gestion des Produits
    </a>
    <div class="navbar-info">
        <span class="navbar-user">👤 <%= user %></span>
        <span class="badge-role <%= isAdmin ? "admin" : "user" %>">
            <%= isAdmin ? "Administrateur" : "Utilisateur" %>
        </span>
        <a href="logout" class="btn-logout">Déconnexion</a>
    </div>
</nav>

<div class="page-wrapper">
	<%if (isUser){ %><h1 class="page-title">Bienvenue</h1>
	<!-- BARRE DE RECHERCHE (visible pour tous) -->
<div style="display:flex;gap:10px;margin-bottom:1.5rem;">
    <input type="text" id="searchInput" 
           class="form-input" 
           placeholder="Rechercher un produit par nom..."
           style="max-width:380px;">
    <button onclick="searchProduit()" class="btn btn-primary btn-sm">Chercher </button>
    <button onclick="resetSearch()" class="btn btn-outline btn-sm">   Reset </button>
</div>
<p id="searchMsg" style="font-size:0.85rem;margin-bottom:1rem;color:var(--bleu);font-weight:600;"></p>
	<%} %>
    <%if (isAdmin){ %><h1 class="page-title">Tableau de bord</h1><%} %>
    <p class="page-subtitle">Liste des produits disponibles</p>

    <!-- STATS -->
    <div class="stats-row">
    <% if (isAdmin) { %>
        <div class="stat-card">
            <div class="stat-value"><%= total %></div>
            <div class="stat-label">Produits</div>
        </div>
        <div class="stat-card">
            <div class="stat-value"><%= String.format("%.0f", totalPrix) %></div>
            <div class="stat-label">Valeur totale</div>
        </div>
        <div class="stat-card">
            <div class="stat-value"><%= total > 0 ? String.format("%.0f", totalPrix/total) : "0" %></div>
            <div class="stat-label">Prix moyen</div>
        </div>
        <% } %>
    </div>


    <!-- TOP BAR -->
    <div class="top-bar">
        <h2 class="page-title" style="font-size:1.3rem;margin:0;">Liste des Produits</h2>
        <% if (isAdmin) { %>
        <a href="ajout.jsp" class="btn btn-success">＋ Ajouter un produit</a>
        <% } %>
    </div>

    <!-- MESSAGE -->
    <%
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
    %>
    <div class="alert alert-success">✅ <%= message %></div>
    <% } %>

    <!-- TABLE -->
    <div class="table-wrap">
        <table class="produits-table">
            <thead>
                <tr>
                    <% if (isAdmin) {%><th>#</th><%}%>
                    <th style="padding:25px;">Nom du produit</th>
                    <th>Prix (DT)</th>
                    <% if (isAdmin) { %><th>Actions</th><% } %>
                </tr>
            </thead>
            <tbody>
            <% if (prod != null && !prod.isEmpty()) {
                for (produits p : prod) { %>
                <tr>
                    <%if (isAdmin) {%><td><strong>#<%= p.getIdProduit() %></strong></td><% } %>       
                    <td style="padding:35px;"><%= p.getNomProduit() %></td>
                    <td><span class="prix-badge"><%= p.getPrix() %> DT</span></td>
                    <% if (isAdmin) { %>
                    <td>
                        <div class="action-btns">
                            <a href="edit?id=<%= p.getIdProduit() %>" class="btn btn-success btn-sm"> Modifier</a>
                            <a href="delete?id=<%= p.getIdProduit() %>"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Supprimer ce produit ?')"> Supprimer</a>
                        </div>
                    </td>
                    <% } %>
                </tr>
            <% } } else { %>
                <tr>
                    <td colspan="<%= isAdmin ? 4 : 3 %>" style="text-align:center;color:#aaa;padding:2rem;">
                        Aucun produit disponible.
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

</div>
<script>
function searchProduit() {
    const input = document.getElementById("searchInput").value.toLowerCase().trim();
    
    if (input === "") {
        resetSearch();
        return;
    }
    
    const rows = document.querySelectorAll(".produits-table tbody tr");
    let found = 0;

    rows.forEach(row => {
        // Cherche dans TOUTES les cellules du rang
        const rowText = row.textContent.toLowerCase();
        if (rowText.includes(input)) {
            row.style.display = "";
            found++;
        } else {
            row.style.display = "none";
        }
    });

    // Message si aucun résultat
    document.getElementById("searchMsg").textContent = 
        found === 0 ? " Aucun produit trouvé pour : " + input : 
                      " " + found + " produit(s) trouvé(s)";
}

function resetSearch() {
    // 1. Vider le champ
    document.getElementById("searchInput").value = "";
    // 2. Effacer le message
    document.getElementById("searchMsg").textContent = "";
    // 3. Remettre TOUTES les lignes visibles
    const rows = document.querySelectorAll(".produits-table tbody tr");
    rows.forEach(row => {
        row.style.display = "table-row"; // ← "table-row" au lieu de ""
    });
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("searchInput").addEventListener("keyup", function(e) {
        if (e.key === "Enter") searchProduit();
    });
});
</script>
</body>
</html>
