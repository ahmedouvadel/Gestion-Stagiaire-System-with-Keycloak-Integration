# Microservices avec Sécurité Keycloak et API Gateway

<p align="center">
  <a href="https://www.youtube.com/watch?v=2nTcqCm5V4E" target="_blank">
    <img src="https://img.youtube.com/vi/2nTcqCm5V4E/0.jpg" alt="Microservices avec Spring Boot et Keycloak" width="600"/>
  </a>
</p>

## Description du Projet
Ce projet implémente une architecture microservices sécurisée avec **Spring Boot**, **Spring Cloud Gateway**, et **Keycloak** comme fournisseur d'authentification et d'autorisation. Il comprend les services suivants :

- **Config Service** : Centralise la gestion des configurations pour tous les microservices.
- **Discovery Service (Eureka)** : Fournit l'enregistrement et la découverte des microservices.
- **Gateway Service** : Gère le routage et la sécurité des microservices.
- **Stagiaire Service** : Gère les informations des stagiaires.
- **Project Service** : Gère les informations des projets.
- **Evaluation Service** : Gère les évaluations des stagiaires.
- **Supervisor Service** : Gère les informations des superviseurs.

## Fonctionnalités
1. **Architecture Microservices** : Chaque service est isolé et communique via un API Gateway.
2. **Sécurité avec Keycloak** : Gestion des utilisateurs, des rôles et des tokens JWT.
3. **Gestion centralisée des configurations** avec Spring Cloud Config.
4. **Découverte des services** avec Eureka.
5. **Tests des services avec Postman** pour créer et manipuler des entités.

## Diagramme de Classes
Les relations entre les entités sont décrites comme suit :
- **Supervisor → Project** : Relation OneToMany (un superviseur peut superviser plusieurs projets).
- **Project → Stagiaire** : Relation ManyToOne (un projet peut être attribué à un stagiaire et un stagiaire peut participer à plusieurs projets).
- **Project → Supervisor** : Relation ManyToOne (un projet est supervisé par un superviseur).
- **Stagiaire → Evaluation** : Relation OneToMany (un stagiaire peut avoir plusieurs évaluations).

## Sécurité avec JWT et Keycloak
Le projet utilise **Keycloak** pour :
1. Créer des utilisateurs avec des rôles spécifiques (ADMIN et USER).
2. Gérer l'authentification et l'autorisation.
3. Valider les tokens JWT pour sécuriser les routes API.

### Exemple de Configuration dans Gateway :
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/Gestion-Stagiaire
```

## Test des API avec Postman
Des requêtes POST sont disponibles pour ajouter des entités. Exemple :
- Ajouter un stagiaire : `POST http://localhost:8081/api/stagiaire`
- Ajouter un projet : `POST http://localhost:8082/api/project`

Inclure un token JWT valide dans les en-têtes :
```
Authorization: Bearer <token>
```

## Captures d'Écran Keycloak
- Création du Realm.
- Définition des utilisateurs et des rôles.
- Attribution des rôles aux utilisateurs.
- Génération et validation des tokens JWT.

## Démonstration Vidéo
Pour une démonstration complète de l'architecture et des fonctionnalités, cliquez sur l'image ci-dessus ou regardez directement sur YouTube :
[Microservices avec Spring Boot et Keycloak](https://www.youtube.com/watch?v=2nTcqCm5V4E)

## Prérequis
- Java 17 ou version ultérieure.
- Maven.
- Docker (facultatif pour Keycloak).
- Postman pour tester les API.

## Exécution du Projet
1. Démarrer Keycloak et créer les utilisateurs et rôles nécessaires.
2. Lancer les microservices dans l'ordre suivant :
   - Config Service
   - Discovery Service
   - Gateway Service
   - Microservices (Stagiaire, Project, Evaluation, Supervisor)
3. Utiliser Postman pour tester les endpoints sécurisés avec des tokens JWT.

---
**Conclusion :**
Ce projet illustre une implémentation robuste et sécurisée d'une architecture microservices avec JWT et Keycloak. Il offre une gestion centralisée des configurations et une sécurité avancée pour les échanges de données.
