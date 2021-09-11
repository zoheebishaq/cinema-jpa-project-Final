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
<div class="text-center mt-2 container">
    <div class="d-grid gap-2 mb-3">
        <a href="/film/${film.id}" class="btn btn-secondary">Retour à ${film.titre}</a>
        <a href="/personne/${personne.id}/edit?film=${film.id}" class="btn btn-primary">Modifier</a>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-12 mb-3">
            <img class="img-fluid" src="/images/photos/${personne.photo}"/>
        </div>
        <div class="col-md-8 col-sm-12 mb-3">
            <h2>${personne.nom} ${personne.prenom}</h2>
            <h6>(${personne.anneeNaiscance})</h6>
            <#if personne.filmsRealises??>
                <div class="d-flex flex-row justify-content-around flex-wrap mt-4">
                    <#list personne.filmsRealises as film>
                        <a href="/film/${film.id}">
                            <div class="card" style="width: 10rem;">
                                <img src="/images/affiches/${film.afficheNom}" class="card-img-top"/>
                                <div class="card-body">
                                    <h5 class="card-title">${film.titre}</h5>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>
            </#if>
            <#if personne.roles??>
                <div class="d-flex flex-row justify-content-around flex-wrap mt-4">
                    <#list personne.roles as role>
                        <a href="/film/${role.film.id}" style="text-decoration: none;">
                            <div class="border mb-3 rounded p-3 d-flex flex-column justify-content-center align-items-center" style="width:250px;height:250px;">
                                <img src="/images/affiches/${role.film.afficheNom}" class="img-thumbnail"
                                     style="max-height: 150px; max-width: 100px;"/>
                                <h5 class="card-title" style="text-decoration: underline;">${role.film.titre}</h5>
                                <p class="card-text" style="color:black;">${role.name}</p>
                            </div>
                        </a>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
</div>
</body>
</html>