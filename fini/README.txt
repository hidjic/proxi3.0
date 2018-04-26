Cher client : 

Comment lancer l'application Java ProxiBanqueSI ?


Pré-requis:
	    - Installer le JDK 8 (ou superieur) sur la machine cible, 

		    (Téléchargé depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html).

	    - Machine cible dote d'un systeme d'exploitation Windows.

Execution :
		- Installer la base de données à l'aide du script : "proxibanquebdd.sql" que vous trouverez dans le dossier BDD de ce répertoire.
		- Lancer tomCat;
		- Installer les applications dans votre tomCAt à l'aide des fichiers : "Proxibanque_V3.war" et "webServiceProxiBanque" que vous trouverez dans de ce dossier. Vous pouvez également récupérer ces fichers via git-hub avec l'url suivante : https://github.com/MehdiColbert/ProxiBanque_V3
		- lancer votre navigateur préféré et rentrer l'url suivant : " localhost:8080/Proxibanque_V3 "

Consulter la documentation: 
	    - Double-cliquer sur le fichier index.html se trouvant dans le repertoire "Proxibanque_V3/doc/" ainsi que : "webServiceProxiBanque/doc/"livré avec les fichiers war ou dans le dossier des projets respectif.
		
		
ATTENTION : INFOS BDD		
		- la base de données doit avoir un user = root et un password = root !!


*******************************Liste des mots de passe du *******************
Il n'existe qu'un seul conseiller enregistré en bdd


login : test
mots de pass : 123


***************************** Notes aux formateurs *****************************

Un conseiller peut gérer une infinité de client (user). Il n'y a pas de restriction.
Un client ne peut possèder que deux comptes (courant et epargne).

Pour une raison inconnue, certains bouttons doivent être cliqués deux fois afin d'être activiés

L'ensemble des fonctionnalités sont réalisées via des web services.

Devant l'ampleur du challenge que nous devions rélévé, Nous avons préféré nous concentrer sur les fonctionnalités les plus importantes. Ainsi d'autres fonctionnalités sont en cours de construction.

Le "virement compte à compte" n'a pas pu être implémenté. Vous avez cependant la possibilité de créditer ou débiter un compte. 

Les fonctionnalités "modifier client" et "supprimer client" sont également en cours de construction.
Si vous souhaitez revoir la liste des fonctionnalités et leur hiérarchie, ouvrez simplement le fichier fonctionnalites.png situé dans le dossier UML.

L'implémentation de jersey 2 n'a malhereusement pas aboutie. Nous avons pu cependant utiliser la version 1.8 de ce FRAMWORK.

Une partie de la javaDoc n'a pas pu être générée (celle du package service du projet : client)


Nous avons également évalué le temps nécessaire afin d'implémenter ces dernières fonctionnalitées.
	-Jersey 2.0 : 1/2 journée (1heure conception et identification des zones de code à modifier;; 1 heure de developpement ;; 3 heures de test/debogage) 
	-log4J : 1/2journée (1heure conception ;; 2heure de developpement ;; 2heures de test)
	-modifier client + delete client : 2h30 1/2heure conception ;; 2heure de developpement ;; 3 heures de test)
	( Virement compte à compte : 1/2 journée (1heure conception ;; 1heure de developpement ;; 3 heures de test)
	

Cout : 400€/J (4000€/mois/developpeur * 2) ==> 800€ TTC \o/ $$ \o/




