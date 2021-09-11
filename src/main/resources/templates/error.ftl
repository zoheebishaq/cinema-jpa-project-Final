<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Cinéma</title>
</head>
<body>
<div class="text-center p-2" style="width:100%;background:#dfdfdf;">
    <h1>Bienvenue au cinéma</h1>
    <p class="pb-2">Voici vos films préférés avec leur affiche, leur distribution et leur notation.</p>
</div>
<div class="text-center mt-3 container">
    <#if code??><h2 class="mb-3">Erreur ${code}</h2></#if>
    <#if raison != ""><h3 class="h4">${raison}</h3><p class="fw-lighter text-secondary mb-3">Il semblerait que tu essaies de mettre à mal notre serveur, ton adresse IP à été enregistrée.</p></#if>
    <div class="d-grid gap-2">
        <a class="btn btn-secondary" href="/">Retour à l'accueil</a>
    </div>
</div>
</body>
</html>