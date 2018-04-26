Cher client : 

Comment lancer l'application Java ProxiBanqueSI ?


Pr�-requis:
	    - Installer le JDK 8 (ou superieur) sur la machine cible, 

		    (T�l�charg� depuis: http://www.oracle.com/technetwork/java/javase/downloads/index.html).

	    - Machine cible dote d'un systeme d'exploitation Windows.

Execution :
		- Installer la base de donn�es � l'aide du script : "proxibanquebdd.sql" que vous trouverez dans le dossier BDD de ce r�pertoire.
		- Lancer tomCat;
		- Installer les applications dans votre tomCAt � l'aide des fichiers : "Proxibanque_V3.war" et "webServiceProxiBanque" que vous trouverez dans de ce dossier. Vous pouvez �galement r�cup�rer ces fichers via git-hub avec l'url suivante : https://github.com/MehdiColbert/ProxiBanque_V3
		- lancer votre navigateur pr�f�r� et rentrer l'url suivant : " localhost:8080/Proxibanque_V3 "

Consulter la documentation: 
	    - Double-cliquer sur le fichier index.html se trouvant dans le repertoire "Proxibanque_V3/doc/" ainsi que : "webServiceProxiBanque/doc/"livr� avec les fichiers war ou dans le dossier des projets respectif.
		
		
ATTENTION : INFOS BDD		
		- la base de donn�es doit avoir un user = root et un password = root !!


*******************************Liste des mots de passe du *******************
Il n'existe qu'un seul conseiller enregistr� en bdd


login : test
mots de pass : 123


***************************** Notes aux formateurs *****************************

Un conseiller peut g�rer une infinit� de client (user). Il n'y a pas de restriction.
Un client ne peut poss�der que deux comptes (courant et epargne).

Pour une raison inconnue, certains bouttons doivent �tre cliqu�s deux fois afin d'�tre activi�s

L'ensemble des fonctionnalit�s sont r�alis�es via des web services.

Devant l'ampleur du challenge que nous devions r�l�v�, Nous avons pr�f�r� nous concentrer sur les fonctionnalit�s les plus importantes. Ainsi d'autres fonctionnalit�s sont en cours de construction.

Le "virement compte � compte" n'a pas pu �tre impl�ment�. Vous avez cependant la possibilit� de cr�diter ou d�biter un compte. 

Les fonctionnalit�s "modifier client" et "supprimer client" sont �galement en cours de construction.
Si vous souhaitez revoir la liste des fonctionnalit�s et leur hi�rarchie, ouvrez simplement le fichier fonctionnalites.png situ� dans le dossier UML.

L'impl�mentation de jersey 2 n'a malhereusement pas aboutie. Nous avons pu cependant utiliser la version 1.8 de ce FRAMWORK.

Une partie de la javaDoc n'a pas pu �tre g�n�r�e (celle du package service du projet : client)


Nous avons �galement �valu� le temps n�cessaire afin d'impl�menter ces derni�res fonctionnalit�es.
	-Jersey 2.0 : 1/2 journ�e (1heure conception et identification des zones de code � modifier;; 1 heure de developpement ;; 3 heures de test/debogage) 
	-log4J : 1/2journ�e (1heure conception ;; 2heure de developpement ;; 2heures de test)
	-modifier client + delete client : 2h30 1/2heure conception ;; 2heure de developpement ;; 3 heures de test)
	( Virement compte � compte : 1/2 journ�e (1heure conception ;; 1heure de developpement ;; 3 heures de test)
	

Cout : 400�/J (4000�/mois/developpeur * 2) ==> 800� TTC \o/ $$ \o/




