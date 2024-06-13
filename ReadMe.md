In Hexagonal architecture, input ports (primary Adaptors) are interfaces that are implemented in the domain layer and used by clients of the domain layer

In Hexagonal architecture, output ports (Secondary Adaptors) are interfaces that are implemented in the infrastructure layer (data access, message modules) and used by the domain layer to reach the infrastructure layers

Note:
- OrderApplicationService - Will be used by client of the application

- RestaurantApprovalResponseMessageListener - message listeners for restaurant approvals
  - DomainEvent Listeners are special type of application services and they are triggered by domain events, not by the clients

