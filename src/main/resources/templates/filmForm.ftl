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
    <h1>Administration cinéma</h1>
</div>
<div class="text-center my-3 container">
    <div class="justify-content-around">
        <div class="d-grid gap-2 mb-3">
            <a href="/film/${film.id}" class="btn btn-secondary">Retour au film</a>
        </div>
        <form method="post" name="film">

            <!-- Titre du film !-->
            <div class="mb-3">
                <label for="titre" class="form-label">Titre du film :</label>
                <input required name="titre"
                       type="text" id="titre" class="form-control"
                       placeholder="Titre du film"
                        <#if film??>
                            value="${film.titre}"
                        </#if>
                />
            </div>

            <!-- Résumé !-->
            <div class="mb-3">
                <label for="resume" class="form-label">Résumé :</label>
                <textarea rows="8" class="form-control" id="resume" name="resume"
                          placeholder="Résumé du film"><#if film??>${film.resume}</#if></textarea>
            </div>

            <!-- Affiche du film !-->
            <div class="mb-3">
                <label for="afficheNom" class="form-label">Affiche du film :</label>
                <input required name="afficheNom"
                       type="text" id="afficheNom" class="form-control"
                       placeholder="Par défaut: placeholder.png"
                        <#if film??>
                            value="${film.afficheNom}"
                        <#else>
                            value="placeholder.png"
                        </#if>
                />
            </div>

            <!-- Réalisateur !-->
            <div class="mb-3">
                <label for="realisateur" class="form-label">Réalisateur :</label>
                <select name="realisateur" id="realisateur" class="form-select">
                    <#list personnes as personne>
                        <option
                                value="${personne.id}"
                                <#if personne.filmsRealises?? && film??>
                                    <#list personne.filmsRealises as realFilm>
                                        <#if realFilm.id == film.id>
                                            selected
                                        </#if>
                                    </#list>
                                </#if>
                        >${personne.nom} ${personne.prenom}</option>
                    </#list>
                </select>
            </div>

            <!-- Bouton éditer/ajouter !-->
            <div class="d-grid gap-2">
                <#if film??>
                    <button class="btn btn-primary">Éditer</button>
                <#else>
                    <button class="btn btn-success">Ajouter</button>
                </#if>
            </div>

        </form>

        <!-- Rôles et acteurs du film !-->
        <#if film??>
            <div class="mt-5">

                <!-- Ajouter un rôle !-->
                <p class="mt-3">Ajouter un rôle :</p>
                <div class="row mb-3">
                    <div class="col-5 mb-3">
                        <select class="form-select" id="actor">
                            <#list personnes as personne>
                                <option value="${personne.id}">${personne.nom} ${personne.prenom}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-5 mb-3">
                        <input type="text" class="form-control" placeholder="Rôle" id="roleName"/>
                    </div>
                    <div class="col-2 mb-3">
                        <a href="#" class="btn btn-sm btn-success" id="addButton">+</a>
                    </div>
                </div>

                <!-- Liste des rôles !-->
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Acteur</th>
                        <th>Rôle</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="rolesTable">
                    <#assign countRoles = 0>
                    <#list personnes as personne>
                        <#if personne.roles?? && film??>
                            <#list personne.roles as role>
                                <#if role.film.id == film.id>
                                    <#assign countRoles = countRoles + 1>
                                    <tr>
                                        <td>${personne.nom} ${personne.prenom}</td>
                                        <td>${role.name}</td>
                                        <td><a href="#" id="${role.id}"
                                               class="btn btn-sm btn-danger">X</a></td>
                                    </tr>
                                </#if>
                            </#list>
                        </#if>
                    </#list>
                        <tr id="noRoles"
                                <#if (countRoles == 0)>
                                    style="display:table-row;"
                                <#else>
                                    style="display:none;"
                                </#if>
                        >
                            <td colspan="3">Il n'y a pas encore de rôles ajoutés à ce film</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </#if>

    </div>
</div>
<#if film??>
    <script>

        // Récupération de tous les éléments HTML nécessaires
        let addButton = document.querySelector('#addButton');
        let actor = document.querySelector('#actor');
        let roleName = document.querySelector('#roleName');
        let rolesTable = document.querySelector('#rolesTable');
        let rolesButtons = document.querySelectorAll('#rolesTable a');
        let lignePasDeRoles = document.querySelector('#noRoles');

        // Évènement 'onClick' sur le bouton "+" (ajouter un rôle)
        addButton.addEventListener('click', (e) => {
            e.preventDefault(); // Annule la redirection vers le lien (car le bouton est un lien)
            const body = new FormData(); // Créé un faux formulaire
            body.append('name', roleName.value); // Rempli le formulaire avec un input name="name" avec pour valeur le nom du rôle
            body.append('actorID', actor.value); // Rempli le formulaire avec un input name="actorID" avec pour valeur l'id de l'acteur assigné au rôle
            /*
            Envoi le formulaire à la page /role/add/ID_DU_FILM
            Le formulaire sera donc géré par le controller qui gère cette route
            (dans ce cas APIController)
            */
            fetch('/role/add/${film.id}', {
                method: 'POST', // Méthode POST (donc @PostMapping)
                body: body // Le corps de la requête HTTP contiendra le formulaire
            }).then((reponse) => {
                reponse.json().then(resultat => { // Quand on reçois le résultat de la requête
                    // On créé une ligne de tableau avec 3 cases
                    let tr = document.createElement('tr');
                    let tdNomActeur = document.createElement('td');
                    let tdNomRole = document.createElement('td');
                    let tdBouton = document.createElement('td');
                    tdNomActeur.innerHTML = resultat['actorName']; // Dans la première case on met le nom de l'acteur reçu depuis l'API
                    tdNomRole.innerHTML = roleName.value; // Dans la deuxième case on met le nom du rôle qu'on a entré dans le formulaire
                    tdBouton.innerHTML = '<a href="#" id="' + resultat['roleID'] + '" class="btn btn-sm btn-danger">X</a>'; // Dans la troisième case on met le bouton pour supprimer le rôle
                    tr.append(tdNomActeur, tdNomRole, tdBouton); // On met les 3 cases dans la ligne du tableau (les 3 <td> dans le <tr>)
                    rolesTable.append(tr); // On met la ligne du tableau dans le tableau des rôles du film
                    roleName.value = ""; // On vide le champ "nom du rôle" dans le formulaire
                    if(lignePasDeRoles.style.display === 'table-row') { // Si la ligne "Pas de rôles ajoutés..." est affichée
                        lignePasDeRoles.style.display = 'none'; // On la cache
                    }
                });
            });
        });

        // Pour chaque bouton "x" (supprimer un rôle)
        rolesButtons.forEach(bouton => {
            // On ajoute un évènement 'onClick' sur CHAQUE bouton "x"
            bouton.addEventListener('click', (element) => {
                element.preventDefault(); // Annule la redirection vers le lien (car le bouton est un lien)
                const boutonDelete = element.target; // target représente le lien sur lequel on a cliqué
                const roleID = boutonDelete.id; // récupère l'id du rôle qui est écrit dans l'id du lien
                /*
                Envoi une requête à la page /role/ID_DU_ROLE/delete
                Le formulaire sera donc géré par le controller qui gère cette route
                (dans ce cas APIController)
                */
                fetch('/role/' + roleID + '/delete').then(() => { // Quand on reçois le résultat de la requête
                    const caseBouton = boutonDelete.parentNode; // Récupère la case du tableau où se trouve le lien sur lequel on à cliqué
                    const ligneBouton = caseBouton.parentNode; // Récupère la ligne du tableau où se trouve le lien sur lequel on à cliqué
                    ligneBouton.remove(); // Supprime la ligne du tableau du rôle qu'on vient de supprimer
                    if(rolesTable.querySelectorAll('tr').length === 1) { // Si dans le tableau on ne trouve que 1 <tr> c'est la ligne "Pas de rôles ajoutés..."
                        if(lignePasDeRoles.style.display === 'none') { // Si cette ligne n'est pas affichée
                            lignePasDeRoles.style.display = 'table-row'; // On l'affiche en tant que ligne de tableau
                        }
                    }
                });
            });
        });
    </script>
</#if>
</body>
</html>