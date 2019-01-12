Projet Okonekat
équipe G PinHead

pour executer le projet

via maven pour le rendu : mvn exec:java
via java :
java -cp target/Okonekat-1.0-SNAPSHOT.jar PinHead.App

les options :

-h pour l'aide
-j <typeRobot,nomRobot> pour ajouter un robot
-i pour les info à la fin de chaque tour
-a affiche les actions de chaque joueur
-d affiche les erreurs
-n <nombreDePartie> indique le nombre de parties à exécuter

les types de robots sont :
Bob : fait des actions de manières séquentiels
Marcel : applique des stratégies de résolution
MarcelJardinier : marcel focalisé sur les objectifs Jardinier
MarcelPanda : marcel focalisé sur les objectifs Panda


Le rendu :
exécution de 1000 paries de bob contre marcel puis 1000 parties de MarcelPanda contre MarcelJardinier.
