<!doctype html>
<html lang="fr">
<head>
    <style type="text/css">
        * {
            margin:0;
            padding:0;
        }
    </style>
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
<div class="text-center mt-2 container">
    <ul style="list-style-type: none;padding:0;">

        <!-- Liste des films !-->
        <#list films as film>

            <!-- Conteneur d'un film !-->
            <li class="mb-3">
                <div class="row">

                    <!-- Première colonne !-->
                    <div class="col-md-4 col-sm-12">
                        <!-- Affiche du film !-->
                        <img width="200px" src="images/affiches/${film.afficheNom}">
                    </div>
                    <!-- Fin de la première colonne !-->

                    <!-- Deuxième colonne !-->
                    <div class="col-md-8 col-sm-12">

                        <!-- Titre du film !-->
                        <h3><a href="/film/${film.id}">${film.titre}</a></h3>

                        <!-- Résumé du film !-->
                        <p>${film.resume}</p>

                        <!-- Affichage des étoiles pour la note !-->
                        <p>
                            <#if film.hasVoted = false><a class="btn btn-danger btn-sm text-light me-2" href="/vote/false/${film.id}"><img src="/images/thumbs-down.svg" /></a></#if>
                            <#list 1..5 as i>
                                <#if (i <= film.note?round)>
                                    <img src="images/etoile_pleine.png" alt="${i}">
                                <#else>
                                    <img src="images/etoile_vide.png" alt=" ">
                                </#if>
                            </#list>
                            <#if film.hasVoted = false><a class="btn btn-success btn-sm ms-2" href="/vote/true/${film.id}"><img class="text-light" src="/images/thumbs-up.svg" /></a></#if>
                        </p>

                    </div>
                    <!-- Fin de la deuxième colonne !-->

                </div>
            </li>
            <!-- Fin du conteneur d'un film !-->

        </#list>

    </ul>
</div>
</body>
</html>