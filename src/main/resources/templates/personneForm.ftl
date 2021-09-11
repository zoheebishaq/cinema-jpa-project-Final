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
<div class="text-center mt-2 container">
    <div class="justify-content-around">
        <div class="d-grid gap-2 mb-3">
            <a href="/personne/${personne.id}?film=${filmID}" class="btn btn-secondary">Retour à ${personne.nom} ${personne.prenom}</a>
        </div>
        <form method="post" name="personne">
            <div class="mb-3">
                <label for="name" class="form-label">Nom :</label>
                <input required name="nom"
                       type="text" id="name" class="form-control"
                       placeholder="Nom"
                        <#if personne??>
                            value="${personne.nom}"
                        </#if>
                />
            </div>
            <div class="mb-3">
                <label for="firstName" class="form-label">Prénom :</label>
                <input required name="prenom"
                       type="text" id="firstName"
                       class="form-control" placeholder="Prénom"
                        <#if personne??>
                            value="${personne.prenom}"
                        </#if>
                />
            </div>
            <div class="mb-3">
                <label for="dateOfBirth" class="form-label">Année de naissance :</label>
                <input required name="anneeNaiscance"
                       type="number" id="dateOfBirth"
                       class="form-control"
                        <#if personne??>
                            value="${personne.anneeNaiscance?trim}"
                        </#if>
                       placeholder="1990" min="1900" max="2021"
                />
            </div>
            <div class="mb-3">
                <label for="photo" class="form-label">Photo :</label>
                <input required name="photo"
                       type="text" id="photo" class="form-control"
                       placeholder="Par défaut: person.png"
                        <#if personne??>
                            value="${personne.photo}"
                        <#else>
                            value="person.png"
                        </#if>
                />
            </div>
            <div class="d-grid gap-2 mb-3">
                <#if personne??>
                    <button class="btn btn-primary">Éditer</button>
                <#else>
                    <button class="btn btn-success">Ajouter</button>
                </#if>
            </div>
        </form>
    </div>
</div>
</body>
</html>