# Fun with GraphQL
This is just a simple Spring GraphQL API that is part of a schema stitching exercise over on [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).

## Next Steps
- Mutations will be added next and we'll see how that comes into play with [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).
- Authorization with Spring Boot Security

## Interesting things to learn from this Project
- Defining Schema Types in `schema.graphql`
- Custom GraphQL Scalar Types for validation
- GraphQLQueryResolvers and how they work with our GraphQL Queries 
- GraphQLResolvers and how they work with our Custom Types
- Async Threads for executing GraphQL Queries
- Integration Test with Spring GraphQL
- Repository Management with r2dbc
- Working with a local H2 Database

## Running this application.
- `./gradlew clean build`
- `./gradlew bootRun`

Application will be exposed on port 8081. You can send your POST request against http://localhost:8081/nintendo/team/graphql.

## Playing with this API 
Checkout the attached Postman Collection for more examples. Below is one for querying all team details

```graphql
query teamData($nintendoId: NintendoId!){
    myName(nintendoId: $nintendoId) {
        nintendoId
        firstName
        middleName
        lastName
    }
    myTeammates(nintendoId: $nintendoId) {
       nintendoId
       teamId
       teamInfo {
           managerId
           teamName
       }
       name {
           firstName
           middleName
           lastName
       }
   }
}
```

```json 
{
    "nintendoId": "nin0001"
}
```
