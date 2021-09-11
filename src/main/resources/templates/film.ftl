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
<div class="text-center p-2" style="width:100%;background:#cfcfcf;">
    <h1>Bienvenue au cinéma</h1>
    <p class="pb-2">Voici vos films préférés avec leur affiche, leur distribution et leur notation.</p>
</div>
<div class="text-center my-3 container">
    <div class="d-grid gap-2 mb-3">
        <a href="/" class="btn btn-secondary">Retour à la liste des films</a>
        <a href="/film/${film.id}/edit" class="btn btn-primary">Modifier</a>
    </div>
    <h2>${film.titre}</h2>
    <div class="row">
        <div class="col-md-4 col-sm-12 mb-3">
            <img width="200px" src="/images/affiches/${film.afficheNom}"/>
        </div>
        <div class="col-md-8 col-sm-12 text-start mb-3">
            <p>${film.resume}</p>
            <#if film.realisateur??>
                Réalisateur : <a href="/personne/${film.realisateur.id}?film=${film.id}">${film.realisateur.nom} ${film.realisateur.prenom}</a> (${film.realisateur.anneeNaiscance})
            </#if>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-around flex-wrap">
        <#list acteursFilm as personne>
            <a href="/personne/${personne.id}?film=${film.id}" style="text-decoration: none;">
                <div class="border mb-3 rounded p-3 d-flex flex-column justify-content-center align-items-center" style="width:250px;height:250px;">
                    <img src="/images/photos/${personne.photo}" class="img-thumbnail"
                         style="max-height: 150px; max-width: 100px;"/>
                    <h5 class="card-title" style="text-decoration: underline;">${personne.nom} ${personne.prenom}</h5>
                    <p class="card-text" style="color:black;">${film.acteurs[personne?index].name}</p>
                </div>
            </a>
        </#list>
    </div>
</div>
</body>
</html>