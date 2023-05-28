# Nearby Parking Finder
Une application “serveur” exposant une API REST pour permettre à une application mobile, ou un site Web, d’afficher la liste des parkings à proximité

# Structure de projet et choix techniques

1. Package By Layer :
   La structure de l'application est divisée en différents packages, ce qui  favorise la séparation des responsabilités et l'organisation du code.
   - Package "client" : Regroupe les classes responsables de la consommation des API externes via WebClient.
   - Package "config" : Contient les classes de configuration de l'application.
   - Package "exception" : Contient des classes d'exceptions personnalisées qui gèrent les erreurs spécifiques à l'application.
   - Package "controller" : Contient les classes des contrôleurs de l'application.
   - Package "dto" : Contient les classes qui représentent les objets de transfert de données.
   - Package "model" : Contient les entités de base de données.
   - Package "service" : Encapsule le logique métier de l'application.
   - Package "repository" : Contient les interfaces responsables de l'accès aux données.
   
   Cette approche favorise la lisibilité, la maintenabilité et l'évolutivité de projet.
   
2. Choix techniques :
   - webClient : Pour la consommation des deux API fournis à fin de récupérer la liste des Parkingss ainsi que leurs places disponibles.
              WebClient offre une manière réactive et non bloquante d'effectuer des appels HTTP, ce qui améliore les performances de l'application et               la scalabilité.
   - mongoDB : Pour la persistance et la gestion des données, mongoDB offre une manipulation de données facile et une flexibilité supérieure par    rapport aux schémas rigides des bases de données SQL.
   - Lombok : Permet de réduire la quantité de code redondant et de simplifier le développement grace aux annotations ( @Data, @Builder, ...)
   - JUnit : Permet d'écrire et d'exécuter des tests unitaires pour vérifier le bon fonctionnement des différentes méthodes implementées.
   - JavaDoc et Swagger : Les annotations JavaDoc permettent de documenter le code de manière claire et précise. Cela facilite la compréhension du code et encourage de bonnes pratiques de développement. De plus, Swagger sert à générer automatiquement une documentation des API basées sur les annotations.
   
3. Réalisation : 
   - Ajouter les sources de données à consumer par ville pour récupérer la liste des parkings ainsi que les informations de leurs disponibilités
  (L'idée est d'avoir une partie back office qui gère les différentes sources de données des parkings par ville).
   ![dataSource](https://github.com/houssemEddineYahia-altersis/parking/assets/127393158/0d623d65-1208-4450-814f-a58ac450b853)
   - Exposer une API REST pour les clients (mobile ou site web) qui prend en entrée le nom de la ville et les coordonnées géométriques (latitude et longitude) et retourne comme réponse la liste des parkings triée par proximité
 (le nom de la ville est nécessaire pour récupérer les deux sources de données des parkings en relation).
 ![nearby](https://github.com/houssemEddineYahia-altersis/parking/assets/127393158/9cdce6e6-ba94-4fd7-8001-743edfd23e40)

## Endpoints

- `POST /api/parking/nearby`: Récupérer la liste des parkings à proximité en fonction de la ville et des coordonnées géométriques reçues d'un mobile ou d'un site internet.
- `POST /api/parkingDataSource`: Ajouter les sources de données des parkings d'une nouvelle ville.
- `GET /api/parkingDataSource/all`: Récupérer toutes les sources de données des parkings pour toutes les villes.
- `GET /api/parkingInfo/{City}`: Récupérer la liste des parkings d'une ville spécifique.
- `GET /api/parkingInfo/availability/{City}`: Récupérer la liste des parkings d'une ville spécifique avec leurs informations de disponibilités.

## Gestion des exceptions

Exemple des exceptions personnalisées :

- `CITY_NOT_FOUND`: Dans le cas où il n'existe pas des sources de données pour la ville demandée dans la base de données.

![city not found request](https://github.com/houssemEddineYahia-altersis/parking/assets/127393158/95c6eb4d-9111-4915-8663-2c8398840e5d)
- `CITY_ALREADY_EXISTS`: Générée lors de l'ajout des sources de données d'une ville qui existe déjà dans la base de données.

![parking data source](https://github.com/houssemEddineYahia-altersis/parking/assets/127393158/b6788c6c-b265-4357-b49e-5af50406cb12)


## Auteur Info

- Nom : Houssem Eddine Yahia
- Email : houssem-eddine.yahia@altersis.com
