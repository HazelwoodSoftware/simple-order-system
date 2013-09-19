Simple Order System
===================

Simple product order system to demo restful api and backbone.js

Project Details
===============
- Model :
-- Product : id, name, description, cost
-- Customer : id, first name, last name, email, adress
-- Order : id, customer id, [product id, quantity]+
- Service urls :
-- GET    /api/products
-- POST   /api/products
-- GET    /api/products/{id}
-- PUT    /api/products/{id}
-- DELETE /api/products/{id}
-- GET    /api/customers
-- POST   /api/customers
-- GET    /api/customers/{id}
-- PUT    /api/customers/{id}
-- DELETE /api/customers/{id}
-- GET    /api/customers/{id}/orders
-- POST   /api/customers/{id}/orders
-- GET    /api/customers/{id}/orders/{id}
-- PUT    /api/customers/{id}/orders/{id}
-- DELETE /api/customers/{id}/orders/{id}
- Webapp urls :
-- /app.html/#/prodcuts
-- /app.html/#/customers
-- /app.html/#/customers/{id}/orders
